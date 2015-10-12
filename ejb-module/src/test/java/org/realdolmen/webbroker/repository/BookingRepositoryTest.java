package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.util.EntityFactory;

/**
 * Created by RDEAX37 on 10/10/2015.
 */
public class BookingRepositoryTest extends DataSetPersistenceTest{

    private BookingRepository bookingRepository;
    private Booking booking;

    @Before
    public void setUp(){
        booking = EntityFactory.createBooking();
        bookingRepository = new BookingRepository();
        bookingRepository.setEntityManager(entityManager());
    }



    @Test
    public void canBookingBeAdded(){
        assertNull(booking.getId());
        bookingRepository.addBooking(booking);
        assertNotNull(booking.getId());
    }
}
