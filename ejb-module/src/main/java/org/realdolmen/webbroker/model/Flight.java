package org.realdolmen.webbroker.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@Entity
public class Flight extends BaseEntity {
    @NotNull
    @ManyToOne (cascade = CascadeType.PERSIST)
    private AirlineCompany company;
    @NotNull
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Airport departure;
    @NotNull
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Airport arrival;
    @NotNull
    @Min(value = 0)
    private Double price;
    @NotNull
    @Min(value = 0)
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
