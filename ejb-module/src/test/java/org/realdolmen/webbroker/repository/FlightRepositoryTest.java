package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.exception.AmbiguousEntityException;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.util.EntityFactory;

/**
 * Created by RDEAX37 on 6/10/2015.
 * Tests for {@link FlightRepository}
 * @author Robin D'Haese
 */
public class FlightRepositoryTest extends DataSetPersistenceTest{

    private FlightRepository flightRepository;
    private Flight flight;

    @Before
    public void setUp(){
        flightRepository = new FlightRepository();
        flightRepository.setEntityManager(entityManager());
        flight = EntityFactory.createFlight();
    }

    @Test
    public void canFlightBeAdded(){
        assertNull(flight.getId());
        flightRepository.add(flight);
        assertNotNull(flight.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotAddInvalidFlight() throws Exception {
        flight.setArrival(null);
        flightRepository.add(flight);
    }

    @Test
    public void canFindFlightBasedOnParameters() throws Exception {
        assertEquals(1, flightRepository.getFlight("Virgin", "Airport1", "Airport2", 150d, 200).size());
        assertEquals(2, flightRepository.getFlight("Virgin", "Airport1", "Airport2", 200d, 200).size());

        assertEquals(0, flightRepository.getFlight("non", "existing", "flight", 150d, 200).size());
    }

    @Test
    public void canFindASingleFlight() throws Exception {
        assertNotNull(flightRepository.getSingleFlight("Virgin", "Airport1", "Airport2", 150d, 200));
        assertNull(flightRepository.getSingleFlight("non", "existing", "flight", 150d, 200));
    }

    @Test(expected = AmbiguousEntityException.class)
    public void cannotFindAmbiguousFlight() throws Exception {
        flightRepository.getSingleFlight("Virgin", "Airport1", "Airport2", 200d, 200);
    }

    @Test
    public void canAllFlightsBeFound(){
        assertEquals(4, flightRepository.getAllFlights().size());
    }
}
