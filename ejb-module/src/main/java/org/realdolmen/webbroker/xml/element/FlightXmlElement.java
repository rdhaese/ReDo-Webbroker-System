package org.realdolmen.webbroker.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YFMAX32 on 7/10/2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "flight")
public class FlightXmlElement {

    @XmlElement(name = "airlineCompany")
    private String airlineCompany;

    @XmlElement(name = "price")
    private Double price;

    @XmlElement(name = "availableSeats")
    private Integer availableSeats;

    @XmlElement(name = "departureAirport")
    private String departureAirport;

    @XmlElement(name = "arrivalAirport")
    private String arrivalAirport;

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}
