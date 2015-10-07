package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.exception.AmbiguousEntityException;
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

    /**
     * Find a single {@link TravelAgency} based on its {@link TravelAgency#name}. Returns <code>null</code> if no
     * travel agency was found with the given name.
     *
     * @param name  The name of the travel agency.
     * @return  The entity corresponding to the travel agency.
     * @throws AmbiguousEntityException if multiple travel agencies with the given name were found.
     */
    public TravelAgency findSingleTravelAgency(String name) throws AmbiguousEntityException {
        List<TravelAgency> agencies = getTravelAgenciesByName(name);
        if (agencies.isEmpty()) {
            return null;
        } else if (agencies.size() > 1) {
            throw new AmbiguousEntityException(agencies.size() + " travel agencies with name '" + name + "' were found.");
        } else {
            return agencies.get(0);
        }
    }

}
