package org.realdolmen.webbroker.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
public class UserXmlElement {

    @XmlElement(required = true)
    protected String firstname;

    @XmlElement(required = true)
    protected String lastname;

    @XmlElement(required = true)
    protected String username;

    @XmlElement(required = true)
    protected String password;

    @XmlElement(required = true)
    protected String role;

    @XmlElement(required = false)
    protected String airlinecompany;

    @XmlElement(required = false)
    protected String travelagency;

    public String getAirlinecompany() {
        return airlinecompany;
    }

    public void setAirlinecompany(String airlinecompany) {
        this.airlinecompany = airlinecompany;
    }

    public String getTravelagency() {
        return travelagency;
    }

    public void setTravelagency(String travelagency) {
        this.travelagency = travelagency;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
