package org.realdolmen.webbroker.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@RequestScoped
@Named
public class LoginController implements Serializable {

    @NotNull(message = "Please enter a username")
    private String username;

    @NotNull(message = "Please enter a password")
    private String password;

    public void login() {
        System.out.println("Login with: " + username + " " + password);
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
}
