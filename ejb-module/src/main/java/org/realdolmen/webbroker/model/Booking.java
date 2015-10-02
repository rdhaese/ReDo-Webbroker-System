package org.realdolmen.webbroker.model;

import org.realdolmen.webbroker.model.user.User;

import java.util.List;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class Booking extends BaseEntity{
    private User bookingUser;
    private Trip trip;
    private Integer numberOfPassengers;
    private List<Discount> discounts;

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
