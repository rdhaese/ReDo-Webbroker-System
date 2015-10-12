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

import static org.jgroups.util.Util.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
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
        controller.setDestination_id(1L);
        controller.setNumberOfPersons(5);
    }

    @Test
    public void canTripsBeSearched() {
        when(airportRepo.find(1L)).thenReturn(EntityFactory.createAirport("a", EntityFactory.createAddress("a", "a", "a", "a", "a"), EntityFactory.createRegion()));
        when(tripRepo.searchTrips(any(), any(), any(), any())).thenReturn(createTripsList());
        String resultPage = controller.searchTrip();
        assertEquals("found-trips", resultPage);
    }

    private List<Trip> createTripsList() {
        List<Trip> trips = new ArrayList<Trip>();
        trips.add(EntityFactory.createTrip());
        trips.add(EntityFactory.createTrip());
        trips.add(EntityFactory.createTrip());
        return trips;
    }

    @Test
    public void isErrorMessageSetIfNoTripsFound() {
        when(tripRepo.searchTrips(any(), any(), any(), any())).thenReturn(new ArrayList<Trip>());
        String resultPage = controller.searchTrip();
        assertEquals("search-trips", resultPage);
        assertTrue(controller.isNoTripsFound());
    }

    @Test
    public void canAllAirportsBeGotten() {
        controller.getAirports();
        verify(airportRepo, times(1)).getAllAirports();
    }
}
