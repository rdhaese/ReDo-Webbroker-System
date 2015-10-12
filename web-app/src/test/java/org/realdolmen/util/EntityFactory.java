package org.realdolmen.util;

import org.realdolmen.webbroker.model.*;
import org.realdolmen.webbroker.model.user.User;

import java.time.LocalDateTime;

/**
 * Factory to create entity objects to be used for testing purposes.
 *
 * @author Youri Flement
 */
public class EntityFactory {

    public static Flight createFlight() {
        Flight flight = new Flight();
        flight.setCompany(createAirlineCompany());
        flight.setAvailableSeats(150);
        flight.setPrice(100.50);
        flight.setArrival(createAirport("Brussels Airport", createAddress("Belgium", "Schendelbeke", "9506", "Dagmoedstraat", "77"), createRegion()));
        flight.setDeparture(createAirport("Amsterdam Airport", createAddress("Netherlands", "Amsterdam", "020", "LuchthavenStraat", "1"), createRegion()));

        return flight;
    }

    public static Trip createTrip() {
        Trip trip = new Trip();
        trip.setFlight(createFlight());
        trip.setTravelAgency(createTravelAgency());
        trip.setAccommodationPrice(100d);
        trip.setStartDate(LocalDateTime.now());
        trip.setEndDate(LocalDateTime.now());
        return trip;
    }

    public static TravelAgency createTravelAgency() {
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.setName("travel agency name");
        return travelAgency;
    }

    public static Airport createAirport(String name, Address address, Region region) {
        Airport airport = new Airport();
        airport.setName(name);
        airport.setAddress(address);
        airport.setRegion(region);
        return airport;
    }

    public static Address createAddress() {
        return createAddress("country", "city", "postal", "street", "number");
    }

    public static Airport createAirport() {
        return createAirport("name", createAddress(), createRegion());
    }

    public static Address createAddress(String country, String city, String postalCode, String street, String number) {
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.setStreet(street);
        address.setNumber(number);
        return address;
    }

    public static Region createRegion() {
        Region region = new Region();
        region.setName("Europe");
        region.setCode("EU");
        return region;
    }

    public static AirlineCompany createAirlineCompany() {
        AirlineCompany ac = new AirlineCompany();
        ac.setName("AirlineCompanyTest");
        return ac;
    }

    public static User createUser(String lName, String fName, String uName, String password) {
        User user = new User();
        user.setFirstName(lName);
        user.setLastName(fName);
        user.setUserName(uName);
        user.setPassword(password);
        return user;
    }

    public static Discount getDiscount(String name, boolean ispercentage, double quantity) {
        Discount discount = new Discount();
        discount.setName(name);
        discount.setIsPercentage(ispercentage);
        discount.setQuantity(quantity);
        return discount;
    }

    public static Booking createBooking() {
        Booking booking = new Booking();
        booking.setBookingUser(EntityFactory.createUser("a", "a", "a", "aaaaaaa"));
        booking.setNumberOfPassengers(10);
        booking.setTrip(EntityFactory.createTrip());
        return booking;
    }

    public static Discount createDiscount() {
        Discount discount = new Discount();
        discount.setName("testDiscount");
        discount.setQuantity(5D);
        discount.setIsPercentage(false);
        return discount;
    }
}
