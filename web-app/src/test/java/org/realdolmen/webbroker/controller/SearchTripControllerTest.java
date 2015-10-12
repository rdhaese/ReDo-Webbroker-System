package org.realdolmen.webbroker.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.realdolmen.util.EntityFactory;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.AirportRepository;
import org.realdolmen.webbroker.repository.TripRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by RDEAX37 on 7/10/2015.
 * Test for {@link SearchTripController}
 *
 * @Author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchTripControllerTest {

    @Mock
    private AirportRepository airportRepo;
    @Mock
    private TripRepository tripRepo;
    @InjectMocks
    private SearchTripController controller;


    @Before
    public void setup() {
        controller.setArrivalDate(Date.from(LocalDate.of(2000, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        controller.setDepartureDate(Date.from(LocalDate.of(2000, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        controller.setDestinationId(1L);
        controller.setNumberOfPersons(5);
    }

    @Test
    public void canTripsBeSearched() {
        when(airportRepo.find(1L)).thenReturn(EntityFactory.createAirport("a", EntityFactory.createAddress("a", "a", "a", "a", "a"), EntityFactory.createRegion()));
        when(tripRepo.searchTrips(any(), any(), any(), any())).thenReturn(createTripsList());
        controller.findTrips();
        assertTrue(controller.getFoundTrips().size() > 0);
    }

    private List<Trip> createTripsList() {
        List<Trip> trips = new ArrayList<>();
        trips.add(EntityFactory.createTrip());
        trips.add(EntityFactory.createTrip());
        trips.add(EntityFactory.createTrip());
        return trips;
    }

    @Test
    public void canSearchTripWithArrivalId() throws Exception {
        when(tripRepo.searchTrips(any(), any(), any(), any())).thenReturn(new ArrayList<>());
        String s = controller.searchTripWithDestination(1L);
        assertEquals("search-trip", s);
        assertEquals(1L, controller.getDestinationId().longValue());
    }

    @Test
    public void searchPerformedFlagIsSet() {
        when(tripRepo.searchTrips(any(), any(), any(), any())).thenReturn(new ArrayList<>());
        controller.findTrips();
        assertTrue(controller.isPerformedASearch());
    }

    @Test
    public void canAllAirportsBeGotten() {
        controller.getAirports();
        verify(airportRepo, times(1)).getAllAirports();
    }
}
