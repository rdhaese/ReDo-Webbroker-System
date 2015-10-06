package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Flight;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by RDEAX37 on 6/10/2015.
 */
@Stateless
@LocalBean
public class FlightRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Flight flight){
        entityManager.persist(flight);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
