package org.realdolmen.webbroker.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.realdolmen.util.EntityFactory;
import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.repository.BookingRepository;
import org.realdolmen.webbroker.repository.FlightRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Mock test for the {@link ConfirmBookingController}.
 *
 * @author Youri Flement
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfirmBookingControllerTest {

    @Mock
    CurrentBookingController currentBookingController;

    @Mock
    BookingRepository bookingRepository;

    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    ConfirmBookingController controller;

    Booking booking;

    @Before
    public void setup() {
        booking = EntityFactory.createBooking();
        when(currentBookingController.getBooking()).thenReturn(booking);
    }

    @Test
    public void canBookIfValidPurchase() throws Exception {
        assertEquals("thank-you", controller.purchase());
        assertTrue(controller.getErrorMessage().isEmpty());
        verify(bookingRepository, times(1)).addBooking(booking);
        verify(flightRepository, times(1)).updateFlight(booking.getTrip().getFlight());
    }

    @Test
    public void cannotOverbookFlight() throws Exception {
        booking.getTrip().getFlight().setAvailableSeats(0);

        assertEquals("confirm-booking", controller.purchase());
        assertFalse(controller.getErrorMessage().isEmpty());
        verifyNoMoreInteractions(bookingRepository);
        verifyNoMoreInteractions(flightRepository);
    }

}

