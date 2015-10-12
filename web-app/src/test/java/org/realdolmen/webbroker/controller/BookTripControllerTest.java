package org.realdolmen.webbroker.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.realdolmen.webbroker.repository.TripRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by RDEAX37 on 8/10/2015.
 * Test for {@link BookTripController}
 * @Author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
public class BookTripControllerTest {

    @Mock
    private CurrentBookingController currentBookingController;
    @Mock
    private TripRepository tripRepository;
    @Mock
    private LoggedInUserController loggedInUserController;

    @InjectMocks
    private BookTripController bookTripController;

    @Before
    public void setUp(){
        when(tripRepository.find(1L)).thenReturn(EntityFactory.createTrip());
        when(loggedInUserController.getLoggedInUser()).thenReturn(EntityFactory.createUser("a", "b", "c", "123456"));
    }

    @Test
    public void canSummaryBeShown(){
        String resultPage = bookTripController.showSummary(1L, 10);
        assertEquals("trip-summary", resultPage);
        verify(tripRepository, times(1)).find(1L);
        verify(currentBookingController, times(1)).setCurrentBooking(any());
    }
}
