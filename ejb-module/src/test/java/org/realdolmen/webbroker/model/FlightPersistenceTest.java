package org.realdolmen.webbroker.model;

import javafx.scene.chart.PieChart;
import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;

import javax.validation.ConstraintViolationException;

/**
 * Created by RDEAX37 on 6/10/2015.
 */

public class FlightPersistenceTest extends DataSetPersistenceTest{

    private Flight flight;

    @Before
    public void setUp(){
        createFlight();
    }

    private void createFlight() {
        flight = new Flight();
        flight.setCompany(getAirlineCompany());
        flight.setAvailableSeats(150);
        flight.setPrice(100.50);
        flight.setArrival(getAirport("Brussels Airport", getAddress("Belgium", "Schendelbeke", "9506", "Dagmoedstraat", "77"), getRegion()));
        flight.setDeparture(getAirport("Amsterdam Airport", getAddress("Netherlands", "Amsterdam", "020", "LuchthavenStraat", "1"), getRegion()));
    }

    private Airport getAirport(String name, Address address, Region region) {
        Airport airport = new Airport();
        airport.setName(name);
        airport.setAddress(address);
        airport.setRegion(region);
        return airport;
    }

    private Address getAddress(String country, String city, String postalCode, String street, String number) {
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.setStreet(street);
        address.setNumber(number);
        return address;
    }

    private Region getRegion() {
        Region region = new Region();
        region.setName("Europe");
        region.setCode("EU");
        return region;
    }

    private AirlineCompany getAirlineCompany() {
        AirlineCompany ac = new AirlineCompany();
        ac.setName("AirlineCompanyTest");
        return ac;
    }

    @Test
    public void canFlightBePersisted(){
        int originalSize = entityManager().createQuery("SELECT f From Flight f", Flight.class).getResultList().size();
        entityManager().persist(flight);
        assertEquals(originalSize + 1, entityManager().createQuery("SELECT f From Flight f", Flight.class).getResultList().size());
    }

    @Test (expected = ConstraintViolationException.class)
     public void flightCantBePersistedWithoutAirlineCompany(){
        flight.setCompany(null);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithoutNumberOfSeats(){
        flight.setAvailableSeats(null);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithNumberOfSeatsSmallerThan0(){
        flight.setAvailableSeats(-10);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithoutPrice(){
        flight.setPrice(null);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithPriceSmallerThan0(){
        flight.setPrice(-1.5);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithoutArrival(){
        flight.setArrival(null);
        entityManager().persist(flight);
    }

    @Test (expected = ConstraintViolationException.class)
    public void flightCantBePersistedWithtoutDeparture(){
        flight.setDeparture(null);
        entityManager().persist(flight);
    }

    @Test
    public void canAllFlightsBeFound(){
        assertEquals(2, entityManager().createQuery("Select f From Flight f").getResultList().size());
    }

    @Test
    public void canFlightBeFoundOnId(){
        assertNotNull(entityManager().find(Flight.class, 1L));
    }
}
