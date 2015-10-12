package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.model.Discount;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class BookingRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void addBooking(Booking booking) {
        List<Discount> mergedDiscounts = new ArrayList<Discount>();
        for (Discount discount : booking.getDiscounts()){
            mergedDiscounts.add(entityManager.merge(discount));
        }
        booking.setDiscounts(mergedDiscounts);
        booking.setBookingUser(entityManager.merge(booking.getBookingUser()));
        booking.getTrip().setFlight(entityManager.merge(booking.getTrip().getFlight()));
        booking.getTrip().setTravelAgency(entityManager.merge(booking.getTrip().getTravelAgency()));
        booking.setTrip(entityManager.merge(booking.getTrip()));
        entityManager.persist(booking);
    }
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Booking> getAllBookings() {
        return entityManager.createQuery("select b from Booking b", Booking.class).getResultList();
    }
}
