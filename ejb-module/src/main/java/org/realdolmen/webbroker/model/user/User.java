package org.realdolmen.webbroker.model.user;

import org.realdolmen.webbroker.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 * Entity representing a User
 * @Author Robin D'Haese
 */
@Entity(name = "users")
@Inheritance
public class User extends BaseEntity {
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private String salt;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
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

    public String getPassword() {
        return password;
    }

    /**
     * @param password
     * @throws IllegalArgumentException If the password length is < 6
     */
    public void setPassword(String password) throws IllegalArgumentException{
        if ((password == null) || (password.length() >= 6)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password should be at least 6 characters long");
        }
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
