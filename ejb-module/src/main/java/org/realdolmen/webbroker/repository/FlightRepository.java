package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.exception.AmbiguousEntityException;
import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.model.Flight;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Repository for the {@link Flight} entity.
 *
 * @author Youri Flement
 */
@Stateless
@LocalBean
public class FlightRepository implements Serializable {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Add a {@link Flight} to the repository. The flight must have a company, arrival airport and departure airport.
     *
     * @param flight The flight to add.
     * @throws IllegalArgumentException if the flight is missing either a company or airport.
     */
    public void add(Flight flight) throws IllegalArgumentException {
        if(flight.getArrival() == null || flight.getDeparture() == null || flight.getCompany() == null) {
            throw new IllegalArgumentException("A flight must have a company, departure airport and arrival airport");
        }
        flight.setCompany(entityManager.merge(flight.getCompany()));
        flight.setArrival(entityManager.merge(flight.getArrival()));
        flight.setDeparture(entityManager.merge(flight.getDeparture()));

        entityManager.persist(flight);
    }

    /**
     * Find {@link Flight}s based on the name of the company, the name of the arrival and departure airport, price and the number of
     * available seats.
     *
     * @param company   The name of the company of the flight.
     * @param departureAirport  The name of the departure airport.
     * @param arrivalAirport    The name of the arrival airport.
     * @param price The price of the flight.
     * @param availableSeats    The available seats on the flight.
     * @return  A list of all flights which math the given parameters.
     */
    public List<Flight> getFlight(String company, String departureAirport, String arrivalAirport, Double price, Integer availableSeats) {
        TypedQuery<Flight> query =
                entityManager.createQuery("select f from Flight f where f.company.name = :company and f.arrival.name = :arrival and f.departure.name = :departure and f.price = :price and f.availableSeats = :seats", Flight.class)
                        .setParameter("company", company)
                        .setParameter("departure", departureAirport)
                        .setParameter("seats", availableSeats)
                        .setParameter("price", price)
                        .setParameter("arrival", arrivalAirport);

        return query.getResultList();
    }

    /**
     * Find a single {@link Flight} based in the name of its company, the name of the arrival and departure airports,
     * the price and the number of available seats.
     *
     * @param company   The name of the company.
     * @param departure The name of departure airport.
     * @param arrival   The name of the arrival airport.
     * @param price The price.
     * @param availableSeats    The number of available flights.
     * @return  The flight corresponding to the given parameters or <code>null</code> if no corresponding flight was found.
     * @throws AmbiguousEntityException if multiple flights with the given parameters were found.
     */
    public Flight getSingleFlight(String company, String departure, String arrival, Double price, Integer availableSeats) throws AmbiguousEntityException {
        List<Flight> flights = getFlight(company, departure, arrival, price, availableSeats);
        if (flights.isEmpty()) {
            return null;
        } else if (flights.size() > 1) {
            throw new AmbiguousEntityException(flights.size() + " flights found for parameters: " + company + "," + departure + "," + arrival + "," + price + "," + availableSeats);
        }
        return flights.get(0);
    }

    /**
     * @return All flights
     */
    public List<Flight> getAllFlights() {
        return entityManager.createQuery("SELECT a From Flight a", Flight.class).getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void updateFlight(Flight flight) {
        entityManager.merge(flight);
    }
}
