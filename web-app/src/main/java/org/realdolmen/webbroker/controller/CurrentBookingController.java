package org.realdolmen.webbroker.controller;


import org.realdolmen.webbroker.model.Booking;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class CurrentBookingController implements Serializable {

    private Booking currentBooking;

    public Booking getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(Booking currentBooking) {
        this.currentBooking = currentBooking;
    }
}
