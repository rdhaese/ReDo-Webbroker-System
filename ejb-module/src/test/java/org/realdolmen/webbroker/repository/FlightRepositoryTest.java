package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.util.EntityFactory;

/**
 * Created by RDEAX37 on 6/10/2015.
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
}
