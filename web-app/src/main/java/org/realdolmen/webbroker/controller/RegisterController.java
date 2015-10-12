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

    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    @NotNull
    private String userName;
    @NotNull
    @Size(min = 6)
    private String password;

    private boolean errorRegistering = false;
    private boolean userNameAlreadyInUse = false;

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
        if (userRepo.getUserByUsername(userName) == null) {
            //Register user
            try {
                userRepo.add(createUser());
            } catch (Exception e) {
                errorRegistering = true;
                return "register-user";
            }
            return "register-success";
        } else {
            //user already exists
            userNameAlreadyInUse = true;
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

    public boolean isErrorRegistering() {
        return errorRegistering;
    }

    public void setErrorRegistering(boolean errorRegistering) {
        this.errorRegistering = errorRegistering;
    }

    public boolean isUserNameAlreadyInUse() {
        return userNameAlreadyInUse;
    }

    public void setUserNameAlreadyInUse(boolean userNameAlreadyInUse) {
        this.userNameAlreadyInUse = userNameAlreadyInUse;
    }
}
