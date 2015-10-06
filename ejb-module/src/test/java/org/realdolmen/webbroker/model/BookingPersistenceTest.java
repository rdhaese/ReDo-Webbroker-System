package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.util.EntityFactory;

import javax.validation.ConstraintViolationException;
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
        booking = new Booking();
        booking.setBookingUser(EntityFactory.createUser("D'Haese", "Robin", "rdhaese", "123456"));
        booking.setTrip(EntityFactory.createTrip());
        booking.setNumberOfPassengers(4);
        booking.setDiscounts(getDiscounts());
    }

    private List<Discount> getDiscounts() {
        List<Discount> discounts = new ArrayList<Discount>();
        discounts.add(EntityFactory.getDiscount("disc1", false, 12.56));
        discounts.add(EntityFactory.getDiscount("disc2", true, 12.90));
        return discounts;
    }

    @Test
    public void canBookingBePersisted(){
        int originalSize = entityManager().createQuery("SELECT b From Booking b", Booking.class).getResultList().size();
        entityManager().persist(booking);
        assertEquals(originalSize + 1, entityManager().createQuery("SELECT b From Booking b", Booking.class).getResultList().size());
    }

    @Test (expected = ConstraintViolationException.class)
    public void bookingCantPersistWithoutBookingUser(){
        booking.setBookingUser(null);
        entityManager().persist(booking);
    }

    @Test (expected = ConstraintViolationException.class)
    public void bookingCantPersistWithoutTrip(){
        booking.setTrip(null);
        entityManager().persist(booking);
    }

    @Test (expected = ConstraintViolationException.class)
    public void bookingCantPersistWithoutPassengers(){
        booking.setNumberOfPassengers(null);
        entityManager().persist(booking);
    }

    @Test (expected = ConstraintViolationException.class)
    public void bookingCantPersistWhenNumberOfPassengersIs0OrLower(){
        booking.setNumberOfPassengers(0);
        entityManager().persist(booking);
    }

    @Test (expected = ConstraintViolationException.class)
    public void bookingCantPersistWhenOverridePriceIsLowerThan0(){
        booking.setOverridePrice(-1D);
        entityManager().persist(booking);
    }

    @Test
    public void canBookingPersistWithNoOverridePrice() {
        booking.setOverridePrice(null);
        entityManager().persist(booking);
    }

    @Test
    public void canAllBookingsBeFound(){
        assertEquals(2, entityManager().createQuery("Select b From Booking b").getResultList().size());
    }

    @Test
    public void canBookingBeFoundOnId(){
        assertNotNull(entityManager().find(Booking.class, 1L));
    }


}
