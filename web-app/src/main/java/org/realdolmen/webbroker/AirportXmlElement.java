package org.realdolmen.webbroker;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "airport")
public class AirportXmlElement {

    @XmlElement
    private String name;

    @XmlElement
    private String regionCode;

    @XmlElement(name = "address", required = true)
    private AddressXmlElement addressXmlElement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCity() {
        return addressXmlElement.city;
    }

    public void setCity(String city) {
        addressXmlElement.city = city;
    }

    public String getCountry() {
        return addressXmlElement.country;
    }

    public void setCountry(String country) {
        addressXmlElement.country = country;
    }

    public String getNumber() {
        return addressXmlElement.number;
    }

    public void setNumber(String number) {
        addressXmlElement.number = number;
    }

    public String getPostalCode() {
        return addressXmlElement.postalCode;
    }

    public void setPostalCode(String postalCode) {
        addressXmlElement.postalCode = postalCode;
    }

    public String getStreet() {
        return addressXmlElement.street;
    }

    public void setStreet(String street) {
        addressXmlElement.street = street;
    }
}
