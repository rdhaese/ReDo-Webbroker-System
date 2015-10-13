package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.repository.TripRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

/**
 * Created by RDEAX37 on 8/10/2015.
 * Controller for the book trip functionality
 * @author Robin D'Haese
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

    public String showSummary() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String persons = params.get("persons");
        String id = params.get("tripId");
        return showSummary(Long.parseLong(id), Integer.parseInt(persons));
    }

    public String showSummary(Long id, int amountOfPersons){
        Booking booking = new Booking();
        booking.setTrip(tripRepo.find(id));
        booking.setNumberOfPassengers(amountOfPersons);
        currentBookingController.setCurrentBooking(booking);
        return "trip-summary";
    }
}
