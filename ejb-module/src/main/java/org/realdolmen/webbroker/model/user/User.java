package org.realdolmen.webbroker.model.user;

import org.realdolmen.webbroker.model.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@Entity
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
