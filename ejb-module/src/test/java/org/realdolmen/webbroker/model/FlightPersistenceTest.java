package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.util.EntityFactory;

import javax.validation.ConstraintViolationException;

/**
 * Created by RDEAX37 on 6/10/2015.
 * Persistence tests for {@link Address}
 * @author Robin D'Haese
 */

public class FlightPersistenceTest extends DataSetPersistenceTest{

    private Flight flight;

    @Before
    public void setUp(){
        flight = EntityFactory.createFlight();
    }

    @Test
    public void canFlightBePersisted(){
        int originalSize = entityManager().createQuery("SELECT f From Flight f", Flight.class).getResultList().size();
        entityManager().persist(flight);
        assertEquals(originalSize + 1, entityManager().createQuery("SELECT f From Flight f", Flight.class).getResultList().size());
    }

    @Test (expected = ConstraintViolationException.class)
     public void flightCantBePersistedWithoutAirlineCompany(){
        flight.setCompany(null);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithoutNumberOfSeats(){
        flight.setAvailableSeats(null);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithNumberOfSeatsSmallerThan0(){
        flight.setAvailableSeats(-10);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithoutPrice(){
        flight.setPrice(null);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithPriceSmallerThan0(){
        flight.setPrice(-1.5);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithoutArrival(){
        flight.setArrival(null);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithtoutDeparture(){
        flight.setDeparture(null);
        entityManager().persist(flight);
    }

    @Test
    public void canAllFlightsBeFound(){
        assertEquals(4, entityManager().createQuery("Select f From Flight f").getResultList().size());
    }

    @Test
    public void canFlightBeFoundOnId(){
        assertNotNull(entityManager().find(Flight.class, 1L));
    }
}
