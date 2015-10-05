package org.realdolmen.webbroker.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@Entity
public class Address extends BaseEntity{
    @NotNull
    private String country;
    @NotNull
    private String street;
    @NotNull
    private String number;
    @NotNull
    private String postalCode;
    @NotNull
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
