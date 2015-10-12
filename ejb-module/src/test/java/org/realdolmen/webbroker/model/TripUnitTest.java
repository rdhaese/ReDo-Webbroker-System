package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.sql.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by RDEAX37 on 8/10/2015.
 * Unit test for {@link Trip}
 * @author Robin D'Haese
 */
public class TripUnitTest {

    private Trip trip;

    @Before
    public void setUp(){
        trip = new Trip();
        trip.setAccommodationPrice(10D);
        trip.setStartDate(LocalDateTime.now());
        trip.setEndDate(LocalDateTime.now().plusDays(10));
        trip.setFlight(createFlight());
    }

    private Flight createFlight() {
        Flight flight = new Flight();
        flight.setPrice(10D);
        flight.setMargin(10);
        return flight;
    }

    @Test
    public void isTotalPriceCalculatedCorrectly(){
        //Expected 10 * 10 + (10 + (10 / 10))= 111
        assertEquals(111D, trip.getTotalPrice(), 0.001);
    }

    @Test
    public void isAmountOfDaysCalculatedCorrectly(){
        assertEquals(10, trip.getAmountOfDays());
    }

}
