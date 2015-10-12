package org.realdolmen.webbroker;

import org.realdolmen.webbroker.model.*;
import org.realdolmen.webbroker.model.user.AirlineCompanyEmployee;
import org.realdolmen.webbroker.model.user.ReDoAirEmployee;
import org.realdolmen.webbroker.model.user.TravelAgencyEmployee;
import org.realdolmen.webbroker.model.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by RDEAX37 on 12/10/2015.
 */
public class DatabaseFiller {

   /* public static void main(String... args){
       for (Booking booking : getAllBookings()){

       }
    }

    private static List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<Booking>();
       bookings.add(createBooking(createUser("ac", "D'Haese", "Robin", "rdhaese", "password", "salt", "Sabena"), createTrip(), createDiscounts(), 5));
        return bookings;
    }

    private static Trip createTrip(String travelAgencyName, Flight flight, double accommodationPrice, LocalDateTime startDate, LocalDateTime endDate) {
        Trip trip = new Trip();
        trip.setFlight(flight);
        trip.setTravelAgency(createTravelAgency(travelAgencyName));
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);
        trip.setAccommodationPrice(accommodationPrice);
        return trip;
    }

    private static Flight createFlight(String airlineCompanyName, Integer margin, Double price, Integer availableSeats) {
        Flight flight = new Flight();
        flight.setCompany(createAirlineCompany(airlineCompanyName));
        flight.setMargin();
        flight.setPrice();
        flight.setAvailableSeats();
        flight.setDeparture();
        flight.setArrival();
        return flight;
    }

    private static User createUser(String userRole, String lastName, String firstName, String userName, String password, String salt, String partnerCompanyName) {
        User user;
        switch (userRole){
            case "ac":
                user = new AirlineCompanyEmployee();
                ((AirlineCompanyEmployee)user).setCompany(createAirlineCompany(partnerCompanyName));
                break;
            case "ta":
                user = new TravelAgencyEmployee();
                ((TravelAgencyEmployee)user).setTravelAgency(createTravelAgency(partnerCompanyName));
                break;
            case "rd":
                user = new ReDoAirEmployee();
                break;
            default:
                user = new User();
        }
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setSalt(salt);
        return user;
    }

    private static TravelAgency createTravelAgency(String name) {
        TravelAgency ta = new TravelAgency();
        ta.setName(name);
        return ta;
    }

    private static AirlineCompany createAirlineCompany(String name) {
        AirlineCompany ac = new AirlineCompany();
        ac.setName(name);
        return ac;
    }

    private static Booking createBooking(User bookingUser, Trip trip, List<Discount> discounts, int numberOfPassengers) {
        Booking booking = new Booking();
        booking.setBookingUser(bookingUser);
        booking.setTrip(trip);
        booking.setNumberOfPassengers(numberOfPassengers);
        booking.setDiscounts(discounts);
    }*/
}
