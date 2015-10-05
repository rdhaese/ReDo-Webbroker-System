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

    public String login() {
        User user = userRepository.getUserByUsername(username);

        // TODO: hashing and salting of input password
        if(user == null || !password.equals(user.getPassword())) {
            loginError = true;
            return "loginForm";
        } else {
            loggedInUserController.setLoggedInUser(user);
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
