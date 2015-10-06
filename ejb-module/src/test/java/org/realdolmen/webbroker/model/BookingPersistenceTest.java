package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.user.User;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RDEAX37 on 6/10/2015.
 */
public class BookingPersistenceTest extends DataSetPersistenceTest {

    private Booking booking;

    @Before
    public void setUp(){
        createBooking();
    }

    private void createBooking() {

    }

    @Test
    public void canBookingBePersisted(){

    }

    private User bookingUser;
    private Trip trip;
    private Integer numberOfPassengers;
    private List<Discount> discounts = new ArrayList<Discount>();

    @Test
    public void bookingCantPersistWithoutBookingUser(){

    }

    @Test
    public void bookingCantPersistWithoutTrip(){

    }

    @Test
    public void bookingCantPersistWithoutPassengers(){

    }

    @Test
    public void bookingCantPersistWhenNumberOfPassengersIs0OrLower(){

    }

    @Test
    public void canAllBookingsBeFound(){

    }

    @Test
    public void canBookingBeFoundOnId(){
        assertNotNull(entityManager().find(Booking.class, 1L));
    }


}
