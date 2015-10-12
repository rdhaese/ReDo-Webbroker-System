package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Region;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class RegionRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Region> getAllRegions() {
        return entityManager.createQuery("select r from Region r", Region.class).getResultList();
    }

    public String getRegionCodeFromContinent(String continent) {
        try {
            return entityManager.createQuery("select r from Region r where r.name = :continent", Region.class)
                    .setParameter("continent", continent)
                    .getSingleResult()
                    .getCode();
        } catch (NoResultException e) {
            return "";
        }
    }
}
