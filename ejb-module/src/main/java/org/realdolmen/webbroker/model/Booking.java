package org.realdolmen.webbroker.model;

import org.realdolmen.webbroker.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RDEAX37 on 2/10/2015.
 * Entity represeting a booking
 * @Author Robin D'Haese
 */
@Entity
public class Booking extends BaseEntity{
    @NotNull
    @OneToOne (cascade = CascadeType.ALL)
    private User bookingUser;
    @NotNull
    @OneToOne (cascade = CascadeType.ALL)
    private Trip trip;
    @NotNull
    @Min (value = 1)
    private Integer numberOfPassengers;
    @Min(value = 0)
    private Double overridePrice;
    @ManyToMany (cascade = CascadeType.ALL)
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

    public Double getOverridePrice() {
        return overridePrice;
    }

    public void setOverridePrice(Double overridePrice) {
        this.overridePrice = overridePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (getBookingUser() != null ? !getBookingUser().equals(booking.getBookingUser()) : booking.getBookingUser() != null)
            return false;
        if (getTrip() != null ? !getTrip().equals(booking.getTrip()) : booking.getTrip() != null) return false;
        if (getNumberOfPassengers() != null ? !getNumberOfPassengers().equals(booking.getNumberOfPassengers()) : booking.getNumberOfPassengers() != null)
            return false;
        if (getOverridePrice() != null ? !getOverridePrice().equals(booking.getOverridePrice()) : booking.getOverridePrice() != null)
            return false;
        return !(getDiscounts() != null ? !getDiscounts().equals(booking.getDiscounts()) : booking.getDiscounts() != null);

    }

    @Override
    public int hashCode() {
        int result = getBookingUser() != null ? getBookingUser().hashCode() : 0;
        result = 31 * result + (getTrip() != null ? getTrip().hashCode() : 0);
        result = 31 * result + (getNumberOfPassengers() != null ? getNumberOfPassengers().hashCode() : 0);
        result = 31 * result + (getOverridePrice() != null ? getOverridePrice().hashCode() : 0);
        result = 31 * result + (getDiscounts() != null ? getDiscounts().hashCode() : 0);
        return result;
    }
}
