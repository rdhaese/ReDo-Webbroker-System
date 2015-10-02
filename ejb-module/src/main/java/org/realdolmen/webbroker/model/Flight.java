package org.realdolmen.webbroker.model;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class Flight extends BaseEntity {
    private AirlineCompany company;
    private Airport departure;
    private Airport arrival;
    private Double price;
    private Integer availableSeats;

    public AirlineCompany getCompany() {
        return company;
    }

    public void setCompany(AirlineCompany company) {
        this.company = company;
    }

    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
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
}
