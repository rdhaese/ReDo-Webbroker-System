package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Controller for the login functionality, based on a username and password.
 *
 * @author Youri Flement
 */
@SessionScoped
@Named
public class LoginController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Inject
    UserRepository userRepository;

    @Inject
    LoggedInUserController loggedInUserController;

    @NotNull(message = "Please enter a username")
    private String username;

    @NotNull(message = "Please enter a password")
    private String password;

    // Flag to determine whether the current attempt to login was successful or not.
    private boolean loginError = false;

    private String prevPage;

    /**
     * Attempt to login with the provided credentials. The user is redirected to the login form if the login
     * was not successful and is redirected to the homepage if the login was successful.
     *
     * @return The homepage if the login was successful, the login form otherwise.
     */
    public String login() {
        loginError = false;
        User user = userRepository.getUserByUsername(username);
        // TODO: hashing and salting of input password
        if (user == null || !password.equals(user.getPassword())) {
            LOGGER.warn("Attempted login for username: " + username);
            loginError = true;
            return "loginForm";
        } else {
            LOGGER.info("User '" + username + "' has logged in.");
            loggedInUserController.setLoggedInUser(user);
            if (prevPage != null) {
                return prevPage;
            }
            return "index";
        }
    }


    public String loginFromPreviousPage(String prevPage) {
        this.prevPage = prevPage;
        return "loginForm";
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginError() {
        return loginError;
    }
}
