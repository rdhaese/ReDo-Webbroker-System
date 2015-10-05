package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.user.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoggedInUserController implements Serializable {

    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public String logout() {
        loggedInUser = null;
        return "index";
    }
}
