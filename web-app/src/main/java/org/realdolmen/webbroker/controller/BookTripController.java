package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.TripRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by RDEAX37 on 8/10/2015.
 * Controller for the book trip functionality
 * @Author Robin D'Haese
 */
@Named
@RequestScoped
public class BookTripController {

    @Inject
    private TripRepository tripRepo;
    @Inject
    private LoggedInUserController loggedInUserController;

    private Booking booking;

    public String showSummary(Long id, int amountOfPersons){
        booking = new Booking();
        booking.setTrip(tripRepo.find(id));
        booking.setNumberOfPassengers(amountOfPersons);
        booking.setBookingUser(loggedInUserController.getLoggedInUser());
        return "trip-summary";
    }

    public String bookTrip(){
        //TODO
        return "booking-placed";
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public int getAmountOfPersons() {
        return amountOfPersons;
    }

    public void setAmountOfPersons(int amountOfPersons) {
        this.amountOfPersons = amountOfPersons;
    }
}
