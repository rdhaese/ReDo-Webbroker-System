package org.realdolmen.webbroker.controller;


import org.realdolmen.webbroker.model.*;
import org.realdolmen.webbroker.model.user.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Named
@SessionScoped
public class CurrentBookingController implements Serializable {

    private Booking currentBooking;

    public Booking getCurrentBooking() {
        Booking booking = new Booking();
        booking.setBookingUser(new User("first", "last", "username", "password", "salt"));
        booking.setDiscounts(new ArrayList<>());
        booking.setNumberOfPassengers(3);
        booking.setOverridePrice(150d);
        Trip trip = new Trip();
        trip.setStartDate(LocalDateTime.now());
        trip.setEndDate(LocalDateTime.now().plusDays(10));
        trip.setTravelAgency(new TravelAgency("Virgin"));
        trip.setAccommodationPrice(100d);
        Flight flight = new Flight();
        Address address = new Address();
        address.setStreet("street");
        address.setPostalCode("9000");
        address.setNumber("100");
        address.setCountry("België");
        address.setCity("city");
        flight.setArrival(new Airport("Londen", new Region("EU", "Europe"), address));
        flight.setDeparture(new Airport("Zaventem", new Region("EU", "Europe"), address));
        flight.setPrice(100d);
        flight.setAvailableSeats(20);
        flight.setCompany(new AirlineCompany("Ryanair"));
        trip.setFlight(flight);
        booking.setTrip(trip);
        return booking;
    }

    public void setCurrentBooking(Booking currentBooking) {
        this.currentBooking = currentBooking;
    }
}
