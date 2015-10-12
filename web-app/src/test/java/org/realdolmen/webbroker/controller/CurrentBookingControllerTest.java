package org.realdolmen.webbroker.controller;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.util.EntityFactory;
import org.realdolmen.webbroker.model.Booking;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for the {@link CurrentBookingController}.
 *
 * @author Youri Flement
 */
public class CurrentBookingControllerTest {

    CurrentBookingController controller;

    Booking booking;

    @Before
    public void setup() {
        controller = new CurrentBookingController();
        booking = EntityFactory.createBooking();
    }

    @Test
    public void canKeepTrackOfCurrentBooking() throws Exception {
        controller.setCurrentBooking(booking);

        assertEquals(booking, controller.getCurrentBooking());
    }

}
