package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
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

    @Test
    public void canFindFlightBasedOnParameters() throws Exception {
        assertEquals(1, flightRepository.findFlight("Virgin", "Airport1", "Airport2", 150d, 200).size());
        assertEquals(2, flightRepository.findFlight("Virgin", "Airport1", "Airport2", 200d, 200).size());
        assertEquals(0, flightRepository.findFlight("Virgin", "Airport2", "Airport2", 150d, 200).size());
    }
}
