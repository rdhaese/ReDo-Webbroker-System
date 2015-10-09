package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.i18n.Text;
import org.realdolmen.webbroker.util.Pair;
import org.realdolmen.webbroker.service.PasswordService;
import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Controller for the register functionality.
 *
 * @author Robin D'Haese
 */
@Named
@RequestScoped
public class RegisterController implements Serializable {

    @Inject
    private UserRepository userRepo;

    @Inject
    PasswordService passwordService;

    @NotNull(message = "Last name is required!")
    private String lastName;
    @NotNull(message = "First name is required!")
    private String firstName;
    @NotNull(message = "Username is required!")
    private String userName;
    @NotNull(message = "Password is required!")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;

    private String errorMessage;

    /**
     * Registers a user with the information from the properties,
     * except when the given username is already taken or when something
     * goes wrong with adding the user to the persistence context.
     *
     * Corresponding messages are set, so it is possible to inform the user.
     *
     * @return the next page to navigate to
     */
    public String registerUser() {
        errorMessage = null;
        if (userRepo.getUserByUsername(userName) == null) {
            //Register user
            try {
                userRepo.add(createUser());
            } catch (Exception e) {
                errorMessage = "Something went wrong registering.";
                return "register-user";
            }
            return "register-success";
        } else {
            //user already exists
            errorMessage = "Username is already in use!";
            return "register-user";
        }
    }

    /**
     * Creates a user with the information from the properties
     * @return the created user.
     */
    private User createUser() {
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setUserName(userName);

        Pair<String, String> securePassword = passwordService.createSecurePassword(password);
        user.setSalt(securePassword.getFirst());
        user.setPassword(securePassword.getSecond());

        return user;
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
