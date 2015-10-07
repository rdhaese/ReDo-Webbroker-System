package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.util.EntityFactory;

/**
 * Created by RDEAX37 on 7/10/2015.
 */
public class TripRepositoryTest extends DataSetPersistenceTest {

    private TripRepository tripRepository;
    private Trip trip;

    @Before
    public void setUp(){
        tripRepository = new TripRepository();
        tripRepository.setEntityManager(entityManager());
        trip = EntityFactory.createTrip();
    }

    @Test
    public void canTripBeAdded(){
        assertNull(trip.getId());
        tripRepository.add(trip);
        assertNotNull(trip.getId());
    }
}
