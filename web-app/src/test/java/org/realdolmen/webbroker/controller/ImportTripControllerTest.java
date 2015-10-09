package org.realdolmen.webbroker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.realdolmen.util.EntityFactory;
import org.realdolmen.webbroker.model.TravelAgency;
import org.realdolmen.webbroker.repository.FlightRepository;
import org.realdolmen.webbroker.repository.TravelAgencyRepository;
import org.realdolmen.webbroker.repository.TripRepository;
import org.realdolmen.webbroker.xml.XmlSerializer;
import org.realdolmen.webbroker.xml.element.TripsXmlElement;

import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

import static org.jgroups.util.Util.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Mock test for the {@link ImportTripController}.
 *
 * @author Youri Flement
 */
@RunWith(MockitoJUnitRunner.class)
public class ImportTripControllerTest {

    @InjectMocks
    ImportTripController controller;

    @Mock
    TripRepository repository;

    @Mock
    TravelAgencyRepository travelAgencyRepository;

    @Mock
    FlightRepository flightRepository;

    @Mock
    XmlSerializer serializer;

    @Mock
    Part file;

    @Test
    public void canUploadValidXmlFile() throws Exception {
        InputStream inputStream = getClass().getResource("/trips.xml").openStream();
        XmlSerializer realSerializer = new XmlSerializer();
        TripsXmlElement elements = realSerializer.unmarshalStream(TripsXmlElement.class, inputStream);

        when(serializer.unmarshalStream(any(), any())).thenReturn(elements);
        when(travelAgencyRepository.getSingleTravelAgency(anyString())).thenReturn(new TravelAgency("Jetair"));
        when(flightRepository.getSingleFlight(anyString(), anyString(), anyString(), anyDouble(), anyInt()))
                .thenReturn(EntityFactory.createFlight());

        controller.upload();
        verify(repository, times(2)).add(any());
    }

    @Test
    public void cannotUploadWithNoTravelAgenciesFound() throws Exception {
        InputStream inputStream = getClass().getResource("/trips.xml").openStream();
        XmlSerializer realSerializer = new XmlSerializer();
        TripsXmlElement elements = realSerializer.unmarshalStream(TripsXmlElement.class, inputStream);

        when(serializer.unmarshalStream(any(), any())).thenReturn(elements);
        when(travelAgencyRepository.getSingleTravelAgency(anyString())).thenReturn(null);

        controller.upload();
        verify(repository, times(0)).add(any());
    }

    @Test
    public void cannotUploadWithNoFlightsFound() throws Exception {
        InputStream inputStream = getClass().getResource("/trips.xml").openStream();
        XmlSerializer realSerializer = new XmlSerializer();
        TripsXmlElement elements = realSerializer.unmarshalStream(TripsXmlElement.class, inputStream);

        when(serializer.unmarshalStream(any(), any())).thenReturn(elements);
        when(travelAgencyRepository.getSingleTravelAgency(anyString())).thenReturn(new TravelAgency("Jetair"));
        when(flightRepository.getSingleFlight(anyString(), anyString(), anyString(), anyDouble(), anyInt()))
                .thenReturn(null);

        controller.upload();
        verify(repository, times(0)).add(any());
    }

    @Test
    public void cannotUploadInvalidXmlFile() throws Exception {
        when(serializer.unmarshalStream(any(), any())).thenThrow(new JAXBException(""));
        controller.upload();
        assertFalse(controller.isAddedSuccess());
    }

    @Test
    public void canHandleFileException() throws Exception {
        when(file.getInputStream()).thenThrow(new IOException());
        controller.upload();
        assertFalse(controller.isAddedSuccess());
    }

    @Test
    public void mustSelectAFile() throws Exception {
        controller.upload();
        assertFalse(controller.isAddedSuccess());
    }
}
