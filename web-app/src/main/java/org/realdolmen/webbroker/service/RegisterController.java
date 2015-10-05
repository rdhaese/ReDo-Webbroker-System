package org.realdolmen.webbroker.service;

import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by RDEAX37 on 5/10/2015.
 */
@Named
@RequestScoped
public class RegisterController {

    @Inject
    private UserRepository userRepo;

    @NotNull(message = "Last name is required!")
    private String lastName;
    @NotNull (message = "First name is required!")
    private String firstName;
    @NotNull (message = "Username is required!")
    private String userName;
    @NotNull (message = "Password name is required!")
    @Size (min = 6, message = "Password should be at least 6 characters long!")
    private String password;

    private String errorMessage;

    public String registerPassenger(){
        errorMessage = null;
        if (userRepo.getUserByUsername(userName) == null){
            //Register user
            User user = new User();
            user.setLastName(lastName);
            user.setFirstName(firstName);
            user.setUserName(userName);
            user.setPassword(password);
            userRepo.add(user);
            return "register-success";
        }else {
            //user already exists
            errorMessage = "Username is already in use!";
            return "register-user";
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
