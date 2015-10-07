package org.realdolmen.webbroker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Flight extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private AirlineCompany company;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Airport departure;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (getCompany() != null ? !getCompany().equals(flight.getCompany()) : flight.getCompany() != null)
            return false;
        if (getDeparture() != null ? !getDeparture().equals(flight.getDeparture()) : flight.getDeparture() != null)
            return false;
        if (getArrival() != null ? !getArrival().equals(flight.getArrival()) : flight.getArrival() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(flight.getPrice()) : flight.getPrice() != null) return false;
        return !(getAvailableSeats() != null ? !getAvailableSeats().equals(flight.getAvailableSeats()) : flight.getAvailableSeats() != null);

    }

    @Override
    public int hashCode() {
        int result = getCompany() != null ? getCompany().hashCode() : 0;
        result = 31 * result + (getDeparture() != null ? getDeparture().hashCode() : 0);
        result = 31 * result + (getArrival() != null ? getArrival().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getAvailableSeats() != null ? getAvailableSeats().hashCode() : 0);
        return result;
    }
}
