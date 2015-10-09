package org.realdolmen.webbroker.repository;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;
import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.util.EntityFactory;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by RDEAX37 on 7/10/2015.
 * Tests for {@link TripRepository}
 * @author Robin D'Haese
 */
public class TripRepositoryTest extends DataSetPersistenceTest {

    private TripRepository tripRepository;
    private AirportRepository airportRepository;
    private Trip trip;

    @Before
    public void setUp(){
        tripRepository = new TripRepository();
        tripRepository.setEntityManager(entityManager());
        airportRepository = new AirportRepository();
        airportRepository.setEntityManager(entityManager());
        trip = EntityFactory.createTrip();
    }

    @Test
    public void canTripBeAdded(){
        assertNull(trip.getId());
        tripRepository.add(trip);
        assertNotNull(trip.getId());
    }

    @Test
    public void canTripsBeFoundOnDestinationDatesAndAvailableSeats(){
        Airport airport = airportRepository.find(1L);
        assertEquals(1,tripRepository.searchTrips(airport, LocalDate.of(2000,10,10), LocalDate.of(2000,10,20), 5).size());
    }

    @Test
    public void canTripBeFoundOnId(){
        assertNotNull(tripRepository.find(7000L));
    }
}
