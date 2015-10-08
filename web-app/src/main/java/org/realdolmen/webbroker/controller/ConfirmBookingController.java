package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ConfirmBookingController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmBookingController.class);

    @Inject
    CurrentBookingController currentBookingController;

    private String creditcardNumber = "67034200303993013";

    private String creditcardExpiryDate;

    @Inject
    BookingRepository bookingRepository;

    public String purchase() {
        Booking booking = currentBookingController.getCurrentBooking();
        bookingRepository.addBooking(booking);
        LOGGER.info("New booking: " + booking);

        //TODO: change available seats of flight etc.
        //TODO: what about concurrency ?
        return "thank-you";
    }

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(String creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public String getCreditcardExpiryDate() {
        return creditcardExpiryDate;
    }

    public void setCreditcardExpiryDate(String creditcardExpiryDate) {
        this.creditcardExpiryDate = creditcardExpiryDate;
    }
}
