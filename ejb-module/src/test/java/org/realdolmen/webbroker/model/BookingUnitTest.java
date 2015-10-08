package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by RDEAX37 on 8/10/2015.
 * Unit test for {@link org.realdolmen.webbroker.model.Booking}
 * @Author Robin D'Haese
 */
public class BookingUnitTest {

    private Booking booking;

    @Before
    public void setUp(){
        booking = new Booking();
        booking.setTrip(createTrip(createFlight()));
        booking.setNumberOfPassengers(10);
    }

    private Flight createFlight() {
        Flight flight = new Flight();
        flight.setPrice(10D);
        return flight;
    }

    private Trip createTrip(Flight flight) {
        Trip trip = new Trip();
        trip.setFlight(flight);
        trip.setAccommodationPrice(10D);
        trip.setStartDate(LocalDateTime.now());
        trip.setEndDate(LocalDateTime.now().plusDays(10));
        return trip;
    }

    @Test
    public void isTheFlightPriceCalculatedCorrectly(){
        assertEquals(100D ,booking.getFlightPrice(), 0.001);
    }

    @Test
    public void isTheTotalPriceCalcualtedCorrectly(){
        assertEquals(200D ,booking.getTotalPrice(), 0.001);
    }
}
