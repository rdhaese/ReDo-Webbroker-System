package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.model.Flight;

import static org.jgroups.util.Util.assertEquals;

/**
 * Created by RDEAX37 on 12/10/2015.
 * test for {@link Flight}
 */
public class FlightUnitTest {

    private Flight flight;

    @Before
    public void setUp(){
        flight = new Flight();
        flight.setPrice(10D);
        flight.setMargin(10);
    }

    @Test
    public void isTotalPriceCalculatedCorrectly(){
        assertEquals(11D, flight.getTotalPrice());
    }
}
