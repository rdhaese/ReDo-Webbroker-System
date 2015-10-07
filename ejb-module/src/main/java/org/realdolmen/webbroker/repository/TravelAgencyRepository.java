package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.TravelAgency;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repository for the {@link TravelAgency} entity.
 *
 * @author Youri Flement
 */
@Stateless
@LocalBean
public class TravelAgencyRepository {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Get a list of all travel agencies which have the given name.
     *
     * @param name The name of the travel agency(s).
     * @return The travel agency entities.
     */
    public List<TravelAgency> getTravelAgenciesByName(String name) {
        TypedQuery<TravelAgency> query =
                entityManager.createQuery("select t from TravelAgency t where t.name = :name", TravelAgency.class)
                             .setParameter("name", name);

        return query.getResultList();
    }


}
