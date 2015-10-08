package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.model.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
        tripsToFilter.forEach(new Consumer<Trip>() {
            @Override
            public void accept(Trip trip) {
                if ((trip.getStartDate().isEqual(depDate.atStartOfDay())) || (trip.getStartDate().isAfter(depDate.atStartOfDay()))) {
                    if ((trip.getEndDate().isEqual(arrDate.atStartOfDay())) || (trip.getEndDate().isBefore(arrDate.atStartOfDay()))) {
                        filteredTrips.add(trip);
                    }
                }
            }
        });
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
        Query qry = entityManager.createQuery("Select t From Trip t WHERE t.flight.arrival = :destination AND t.flight.availableSeats > :numberOfPersons").setParameter("destination", destination).setParameter("numberOfPersons", numberOfPersons);
        return qry.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}
