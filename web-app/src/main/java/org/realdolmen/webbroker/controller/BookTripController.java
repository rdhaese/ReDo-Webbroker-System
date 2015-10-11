package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.Booking;
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
    @Inject
    private CurrentBookingController currentBookingController;

    public String test() {
        return "trip-summary";
    }

    public String showSummary(Long id, int amountOfPersons){
        Booking booking = new Booking();
        booking.setTrip(tripRepo.find(id));
        booking.setNumberOfPassengers(amountOfPersons);
        booking.setBookingUser(loggedInUserController.getLoggedInUser());
        currentBookingController.setCurrentBooking(booking);
        return "trip-summary";
    }
}
