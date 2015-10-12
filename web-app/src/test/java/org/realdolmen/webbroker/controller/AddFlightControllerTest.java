package org.realdolmen.webbroker.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.realdolmen.util.EntityFactory;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.model.user.AirlineCompanyEmployee;
import org.realdolmen.webbroker.repository.AirportRepository;
import org.realdolmen.webbroker.repository.FlightRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

/**
 * Created by RDEAX37 on 6/10/2015.
 * Test for {@link AddFlightController}
 * @Author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
public class AddFlightControllerTest {

    @Mock
    private AirportRepository airportRepo;
    @Mock
    private FlightRepository flightRepo;
    @Mock
    private LoggedInUserController loggedInUserController;

    @InjectMocks
    private AddFlightController addFlightController;

    private Flight flight;

    @Before
    public void setUp(){
        flight = EntityFactory.createFlight();
    }


    @Test
    public void isFlightAdded(){
        Mockito.when(loggedInUserController.getLoggedInUser()).thenReturn(getACEmp());

        setFlightControllerState();

        Flight flight = new Flight();
        flight.setArrival(airportRepo.find(addFlightController.getArrival_id()));
        flight.setDeparture(airportRepo.find(addFlightController.getDeparture_id()));
        flight.setPrice(addFlightController.getPrice());
        flight.setAvailableSeats(addFlightController.getAmountOfSeats());
        flight.setCompany(getACEmp().getCompany());

        addFlightController.addFlight();
        Mockito.verify(flightRepo, times(1)).add(Mockito.any(Flight.class));
    }

    private AirlineCompanyEmployee getACEmp() {
        AirlineCompanyEmployee acEmp = new AirlineCompanyEmployee();
        acEmp.setCompany(EntityFactory.createAirlineCompany());
        acEmp.setLastName("a");
        acEmp.setFirstName("b");
        acEmp.setUserName("c");
        acEmp.setPassword("123456");
        return acEmp;
    }

    @Test
    public void departureAndArrivalCannotBeTheSame(){
        setFlightControllerState();
        addFlightController.setDeparture_id(1L);

        addFlightController.addFlight();
        
        assertTrue(addFlightController.isDepAndArrTheSame());
    }

    @Test
    public void isFormDataCleared(){
        Mockito.when(loggedInUserController.getLoggedInUser()).thenReturn(getACEmp());
        setFlightControllerState();

        addFlightController.addFlight();

        assertNull(addFlightController.getAmountOfSeats());
        assertNull(addFlightController.getPrice());
        assertNull(addFlightController.getArrival_id());
        assertNull(addFlightController.getDeparture_id());
    }

    private void setFlightControllerState() {
        addFlightController.setArrival_id(1L);
        addFlightController.setDeparture_id(2L);
        addFlightController.setPrice(flight.getPrice());
        addFlightController.setAmountOfSeats(flight.getAvailableSeats());
    }
}
