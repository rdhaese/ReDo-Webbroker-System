package org.realdolmen.webbroker.controller;


import org.realdolmen.webbroker.model.Booking;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CurrentBookingController implements Serializable{

    public void setCurrentBooking(Booking booking) {

    }

}
