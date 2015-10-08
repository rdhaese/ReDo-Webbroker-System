package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by RDEAX37 on 7/10/2015.
 * <p>
 * Repository for the {@link Trip} entity
 *
 * @author Robin D'Haese
 */

@Stateless
@LocalBean
public class TripRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Adds a trip to the persistence context
     *
     * @param trip to add
     */
    public void add(Trip trip) {
        trip.setFlight(entityManager.merge(trip.getFlight()));
        trip.setTravelAgency(entityManager.merge(trip.getTravelAgency()));
        entityManager.persist(trip);
    }


    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get all trips.
     *
     * @return All the trips in the repository.
     */
    public List<Trip> getAllTrips() {
        return entityManager.createQuery("select t from Trip t", Trip.class).getResultList();
    }
}
