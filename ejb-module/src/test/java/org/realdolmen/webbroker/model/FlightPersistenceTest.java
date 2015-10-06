package org.realdolmen.webbroker.model;

import org.junit.Before;

/**
 * Created by RDEAX37 on 6/10/2015.
 */

public class FlightPersistenceTest {

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
        flight.setArrival(getAirport("Amsterdam Airport", getAddress("Belgium", "Schendelbeke", "9506", "Dagmoedstraat", "77"), getRegion()));
    }

    private void getAddress(String country, String city, String postalCode, String street, String number) {

    }

    private void getRegion() {
        Region region = new Region();
        region.setName("Europe");
        region.setCode("EU");
    }

    private AirlineCompany getAirlineCompany() {
    }

}
