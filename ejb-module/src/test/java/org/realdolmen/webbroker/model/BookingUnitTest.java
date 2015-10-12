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
        booking.setTrip(createTrip());
        booking.setNumberOfPassengers(10);
        booking.getDiscounts().add(createDiscount(10D, true));
        booking.getDiscounts().add(createDiscount(10D, false));
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
        trip.setFlight(createFlight());
        return trip;
    }

    private Flight createFlight() {
        Flight flight = new Flight();
        flight.setPrice(10D);
        flight.setMargin(10);
        return flight;
    }

    @Test
    public void isTotalDiscountCalculatedCorrectly(){
        //expected 10 + (10 + (10/10)) +(((10 * 10) * 10) / 100) * 10 = 121
        assertEquals(121D, booking.getTotalDiscount(), 0.001);
    }

    @Test
    public void isTotalPriceWithoutDiscountCalculatedCorrectly(){
        //expected ((10 * 10 + (10 + (10/10))) * 10 = 1110
        assertEquals(1110D, booking.getTotalPriceWithoutDiscount(), 0.001);

    }

    @Test
    public void isTotalPriceWithDiscountCalculatedCorrectly(){
        //expected (((10 * 10 + (10 + (10/10))) * 10) - (10 + (10 + (10/10)) +(((10 * 10) * 10) / 100) * 10) = 989
        assertEquals(989D, booking.getTotalPriceWithDiscount(), 0.001);
    }
}
