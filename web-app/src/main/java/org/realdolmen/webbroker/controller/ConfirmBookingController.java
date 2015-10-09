package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.BookingRepository;
import org.realdolmen.webbroker.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Controller to purchase a trip (confirmation of a booking).
 *
 * @author Youri Flement
 */
@Named
@RequestScoped
public class  ConfirmBookingController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmBookingController.class);

    @Inject
    CurrentBookingController currentBookingController;

    @Inject
    BookingRepository bookingRepository;

    @Inject
    FlightRepository flightRepository;


    //TODO REMOVE STATE BEFORE PRODUCTION (keep fields!//
    private String creditcardNumber = "67034200303993013";
    private String creditcardExpiryDate = "01/16";
    /////////////////////////////////////////////////////

    private boolean noMoreSeats = false;

    @Transactional
    public String purchase() {
        Booking booking = currentBookingController.getCurrentBooking();
        Trip trip = booking.getTrip();
        Integer availableSeats = trip.getFlight().getAvailableSeats();
        if(availableSeats < booking.getNumberOfPassengers()) {
            noMoreSeats = true;
            return "confirm-booking";
        } else {
            bookingRepository.addBooking(booking);
            trip.getFlight().setAvailableSeats(availableSeats - booking.getNumberOfPassengers());
            flightRepository.updateFlight(trip.getFlight());

            LOGGER.info("New booking: " + booking);

            return "thank-you";
        }
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

    public boolean isNoMoreSeats() {
        return noMoreSeats;
    }

    public void setNoMoreSeats(boolean noMoreSeats) {
        this.noMoreSeats = noMoreSeats;
    }
}
