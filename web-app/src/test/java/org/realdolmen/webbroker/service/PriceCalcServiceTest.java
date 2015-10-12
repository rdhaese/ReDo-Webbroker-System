package org.realdolmen.webbroker.service;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.model.Discount;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.model.Trip;

import java.time.LocalDateTime;
import java.time.Period;

import static org.jgroups.util.Util.assertEquals;

/**
 * Created by RDEAX37 on 12/10/2015.
 * Test for {@link PriceCalcService}
 * @Author Robin D'Haese
 */
public class PriceCalcServiceTest {

    private PriceCalcService service;
    private Flight flight;
    private Trip trip;
    private Booking booking;
    private Discount discount1;
    private Discount discount2;

    @Before
    public void setUp(){
        service = new PriceCalcService();

        flight = createFlight();
        trip = createTrip();
        trip.setFlight(flight);
        booking = new Booking();
        booking.setTrip(trip);
        booking.setNumberOfPassengers(10);
        discount1 = createDiscount(10D, true);
        discount2 = createDiscount(10D, false);
        booking.getDiscounts().add(discount1);
        booking.getDiscounts().add(discount2);
    }

    private Flight createFlight() {
        Flight flight = new Flight();
        flight.setPrice(10D);
        flight.setMargin(10);
        return flight;
    }

    private Discount createDiscount(Double quantity, boolean isPercentage){
        Discount discount = new Discount();
        discount.setQuantity(quantity);
        discount.setIsPercentage(isPercentage);
        return discount;
    }

    private Trip createTrip() {
        Trip trip = new Trip();
        trip.setAccommodationPrice(10D);
        trip.setStartDate(LocalDateTime.now());
        trip.setEndDate(LocalDateTime.now().plusDays(10));
        return trip;
    }

    @Test
    public void isTotalFlightPriceCalculatedCorrectly(){
        assertEquals(11D, service.getTotalFlightPrice(flight));
    }

    @Test
    public void isTotalTripPriceCalculatedCorrectly(){
        //Expected 10 * 10 + (10 + (10 / 10))= 111
        assertEquals(111D, service.getTotalTripPrice(trip));
    }

    @Test
    public void isDaysOftripCalculatedCorrectly(){
        assertEquals(10, service.getDaysOfTrip(trip));
    }

    @Test
    public void isTotalBookingPriceWithoutDiscountCalculatedCorrectly(){
        //expected ((10 * 10 + (10 + (10/10))) * 10 = 1110
        assertEquals(1110D, service.getTotalBookingPriceWithoutDiscount(booking));
    }

    @Test
    public void isTotalBookingPriceWithDiscountCalculatedCorrectly(){
        //expected (((10 * 10 + (10 + (10/10))) * 10) - (10 + (10 + (10/10)) +(((10 * 10) * 10) / 100) * 10) = 989
        assertEquals(989D, service.getTotalBookingPriceWithDiscount(booking));
    }

    @Test
    public void isTotalDiscountForBookingCalculatedCorrectly(){
        //expected 10 + (10 + (10/10)) +(((10 * 10) * 10) / 100) * 10 = 121
        assertEquals(121D, service.getTotalDiscountForBooking(booking));
    }

    @Test
    public void isDiscountTotalCalculatedCorrectly(){
        //expected return 5
        discount1.setQuantity(5D);
        assertEquals(5D, service.getDiscountTotalFor(100D, discount1));
        //Expected return (100 * (10 /100))
        assertEquals(10D, service.getDiscountTotalFor(100D, discount2));
    }
}
