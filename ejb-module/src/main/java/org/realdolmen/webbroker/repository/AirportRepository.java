package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Airport;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by RDEAX37 on 6/10/2015.
 * Repository for the {@link AirportRepository }entity.
 * @Author Robin D'Haese
 */
@Stateless
@LocalBean
public class AirportRepository implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @return All airports
     */
    public List<Airport> getAllAirports() {
        return entityManager.createQuery("SELECT a From Airport a").getResultList();
    }

    /**
     * @param id to find airport for
     * @return the found airport, or null
     */
    public Airport find(long id) {
       return entityManager.find(Airport.class, id);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Airport> findAirportInRegion(String regionCode) {
        return entityManager.createQuery("select a from Airport a where a.region.code = :code", Airport.class)
                        .setParameter("code", regionCode).getResultList();
    }

    public List<Airport> getAirportsInContinent(String continent) {
        return entityManager.createQuery("select a from Airport a where a.region.name = :continent", Airport.class)
                .setParameter("continent", continent).getResultList();
    }
}
