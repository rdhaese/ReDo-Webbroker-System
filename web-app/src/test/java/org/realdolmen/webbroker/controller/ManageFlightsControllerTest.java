package org.realdolmen.webbroker.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.event.RowEditEvent;
import org.realdolmen.util.EntityFactory;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.repository.FlightRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by RDEAX37 on 12/10/2015.
 * test for {@link ManageFlightsController}
 */
@RunWith(MockitoJUnitRunner.class)
public class ManageFlightsControllerTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private ManageFlightsController controller;

    private List<Flight> flights;

    @Before
    public void setUp(){
        initFlightsList();
    Mockito.when(flightRepository.getAllFlights()).thenReturn(flights);
    }

    private void initFlightsList() {
        flights = new ArrayList<Flight>();
        flights.add(EntityFactory.createFlight());
        flights.add(EntityFactory.createFlight());
    }

    @Test
    public void areFlightsReturned(){
        controller.initFlights();
        assertEquals(2, controller.getAllFlights().size());
    }

    @Test
    public void onRowEditTest(){
       RowEditEvent mock =  Mockito.mock(RowEditEvent.class);
        Flight flight = EntityFactory.createFlight();
        Mockito.when(mock.getObject()).thenReturn(flight);
        controller.onRowEdit(mock);
        Mockito.verify(flightRepository).updateFlight(flight);

    }



}
