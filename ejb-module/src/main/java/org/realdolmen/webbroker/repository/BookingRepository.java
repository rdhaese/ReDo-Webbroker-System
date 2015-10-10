package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Booking;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class BookingRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void addBooking(Booking booking) {
        entityManager.persist(booking);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
