package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;

import javax.validation.ConstraintViolationException;

/**
 * Created by RDEAX37 on 6/10/2015.
 */
public class AirportPersistenceTest extends DataSetPersistenceTest{

    private Airport airport;

    @Before
    public void setUp(){
        createAirport();
    }

    private void createAirport() {
        airport = new Airport();
        airport.setName("Brussels Airport");
        airport.setRegion(getRegion());
        airport.setAddress(getAddress());
    }

    private Address getAddress() {
        Address address = new Address();
        address.setCountry("Belgium");
        address.setCity("Zaventem");
        address.setPostalCode("1930");
        address.setStreet("Airportstreet");
        address.setNumber("1");
        return address;
    }

    private Region getRegion() {
       Region region = new Region();
        region.setName("Europe");
        region.setCode("EU");
        return region;
    }

    @Test
    public void canAirportBePersisted(){
        int originalSize = entityManager().createQuery("SELECT a From Airport a", Airport.class).getResultList().size();
        entityManager().persist(airport);
        assertEquals(originalSize + 1, entityManager().createQuery("SELECT a From Airport a", Airport.class).getResultList().size());
    }

    @Test (expected = ConstraintViolationException.class)
    public void airportCantBePersistedWithoutName(){
        airport.setName(null);
        entityManager().persist(airport);
    }

    @Test (expected = ConstraintViolationException.class)
    public void airportCantBePersistedWithoutAddress(){
        airport.setAddress(null);
        entityManager().persist(airport);
    }

    @Test (expected = ConstraintViolationException.class)
    public void airportCantBePersistedWithoutRegion(){
        airport.setRegion(null);
        entityManager().persist(airport);
    }

    @Test
    public void canAllAirportsBeFound(){
        assertEquals(2, entityManager().createQuery("SELECT a FROM Airport a").getResultList().size());
    }

    @Test
    public void canAirportBeFoundOnId(){
        Airport a = entityManager().find(Airport.class, 1L);
        assertNotNull(a);
        assertNotNull(a.getAddress());
        assertNotNull(a.getRegion());
    }



}
