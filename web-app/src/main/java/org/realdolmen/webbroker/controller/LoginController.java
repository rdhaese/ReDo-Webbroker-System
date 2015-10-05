package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@RequestScoped
@Named
public class LoginController implements Serializable {

    @Inject
    UserRepository userRepository;

    @Inject
    LoggedInUserController loggedInUserController;

    @NotNull(message = "Please enter a username")
    private String username;

    @NotNull(message = "Please enter a password")
    private String password;

    private boolean loginError = false;

    /**
     * Attempt to login with the provided credentials. The user is redirected to the login form if the login
     * was not successful and is redirected to the homepage if the login was successful.
     *
     * @return The homepage if the login was successful, the login form otherwise.
     */
    public String login() {
        User user = userRepository.getUserByUsername(username);

        // TODO: hashing and salting of input password
        if(user == null || !password.equals(user.getPassword())) {
            loginError = true;
            return "loginForm";
        } else {
            loggedInUserController.setLoggedInUser(user);
            // TODO: return to page the user was on
            return "index";
        }
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

    public boolean getLoginError() {
        return loginError;
    }
}
