package org.realdolmen.webbroker.model;

import org.realdolmen.webbroker.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@Entity
public class Booking extends BaseEntity{

    @OneToOne (cascade = CascadeType.PERSIST)
    private User bookingUser;
    @OneToOne (cascade = CascadeType.PERSIST)
    private Trip trip;
    @NotNull
    @Min (value = 1)
    private Integer numberOfPassengers;
    @Min(value = 0)
    private Double overridePrice;
    @ManyToMany (cascade = CascadeType.PERSIST)
    private List<Discount> discounts = new ArrayList<Discount>();

    public User getBookingUser() {
        return bookingUser;
    }

    public void setBookingUser(User bookingUser) {
        this.bookingUser = bookingUser;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
