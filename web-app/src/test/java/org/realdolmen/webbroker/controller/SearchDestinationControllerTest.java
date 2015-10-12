package org.realdolmen.webbroker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.LatLng;
import org.realdolmen.util.EntityFactory;
import org.realdolmen.webbroker.repository.AirportRepository;
import org.realdolmen.webbroker.repository.RegionRepository;
import org.realdolmen.webbroker.service.LocationService;

import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Mocking test for the {@link SearchDestinationController}.
 *
 * @author Youri Flement
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchDestinationControllerTest {

    @Mock
    RegionRepository regionRepository;

    @Mock
    AirportRepository airportRepository;

    @Mock
    LocationService locationService;

    @InjectMocks
    SearchDestinationController controller;

    @Test
    public void canFindDestinations() throws Exception {
        PointSelectEvent event = mock(PointSelectEvent.class);
        when(event.getLatLng()).thenReturn(new LatLng(2, 40));
        when(airportRepository.getAirportsInContinent(anyString())).thenReturn(Collections.singletonList(EntityFactory.createAirport()));
        controller.onPointSelect(event);

        assertTrue(controller.getAvailableDestinations().size() == 1);
        verify(locationService).countryCodeToContinent(anyString());
        verify(airportRepository).getAirportsInContinent(anyString());
    }

}
