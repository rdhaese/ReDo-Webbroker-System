package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.model.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class TripRepository implements Serializable {
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

    /**
     * Searches for all trips that correspond to the given parameters.
     * Trips are included when the departure date is equal to or later than the given depDate.
     * Trips are included when the arrival date is equal to or before the given arrDate.
     *
     * @param destination     where trips starts
     * @param depDate         when the trips should start
     * @param arrDate         when the trips should end
     * @param numberOfPersons minimum of available seats
     * @return all found trips
     */
    public List<Trip> searchTrips(Airport destination, LocalDate depDate, LocalDate arrDate, Integer numberOfPersons) {
        List<Trip> foundTrips = performQuery(destination, numberOfPersons);
        List<Trip> filteredTrips = filterOnDates(depDate, arrDate, foundTrips);
        return filteredTrips;
    }

    /**
     *
     * @param depDate to filter on
     * @param arrDate to filter on
     * @param tripsToFilter trips that should be filtered on date
     * @return A list of all trips that where the start date is equal or later than the given depDate and where the end date is equal or before the arrDate
     */
    private List<Trip> filterOnDates(final LocalDate depDate, final LocalDate arrDate, List<Trip> tripsToFilter) {
        List<Trip> filteredTrips = new ArrayList<Trip>();
        for (Trip trip : tripsToFilter){
            LocalDate start = trip.getStartDate().toLocalDate();
            LocalDate end = trip.getEndDate().toLocalDate();

            boolean startIsEqual = depDate.isEqual(start);
            boolean startIsAfter = depDate.isAfter(start);
            boolean endIsEqual = arrDate.isEqual(end);
            boolean endIsBefore = arrDate.isBefore(end);

            boolean validStartDate = startIsEqual || startIsAfter;
            boolean validEndDate = endIsEqual || endIsBefore;

            if (validStartDate && validEndDate) {
                    filteredTrips.add(trip);
            }
        }
        return filteredTrips;
    }

    /**
     * Searches for all trips in the persistence context where the destination is
     * equal to the given one and where there are more availableSeats than the asked number of persons.
     * @param destination to find trips for
     * @param numberOfPersons that should minimum be able to book the trip
     * @return all found trips
     */
    private List<Trip> performQuery(Airport destination, Integer numberOfPersons) {
       Query qry = entityManager.createQuery("Select t From Trip t WHERE t.flight.arrival.name = :destination AND t.flight.availableSeats >= :numberOfPersons").setParameter("destination", destination.getName()).setParameter("numberOfPersons", numberOfPersons);
        return qry.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Find a trip on his id
     * @param id to search trip for
     * @return the found trip, or null
     */
    public Trip find(Long id) {
        return entityManager.find(Trip.class, id);
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
