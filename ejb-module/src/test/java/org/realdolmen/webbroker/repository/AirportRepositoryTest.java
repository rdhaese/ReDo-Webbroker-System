package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;

/**
 * Created by RDEAX37 on 6/10/2015.
 */
public class AirportRepositoryTest extends DataSetPersistenceTest {

    private AirportRepository airportRepository;

    @Before
    public void setUp(){
        airportRepository = new AirportRepository();
        airportRepository.setEntityManager(entityManager());
    }

    @Test
    public void canAllAirportsBeFound(){
        assertEquals(2, airportRepository.getAllAirports().size());
    }

    @Test
    public void canAirportBeFoundOnId(){
        assertNotNull(airportRepository.find(1L));
    }
}
