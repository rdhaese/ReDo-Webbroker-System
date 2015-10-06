package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.AirlineCompany;
import org.realdolmen.webbroker.model.Airport;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by RDEAX37 on 6/10/2015.
 */
@Stateless
@LocalBean
public class AirportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Airport> getAllAirports() {
        return entityManager.createQuery("SELECT a From Airport a").getResultList();
    }

    public Airport find(long id) {
       return entityManager.find(Airport.class, id);
    }
}
