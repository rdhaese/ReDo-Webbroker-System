package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by RDEAX37 on 7/10/2015.
 */

@Stateless
@LocalBean
public class TripRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void add(Trip trip){
        trip.setFlight(entityManager.merge(trip.getFlight()));
        trip.setTravelAgency(entityManager.merge(trip.getTravelAgency()));
        entityManager.persist(trip);
    }


    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
