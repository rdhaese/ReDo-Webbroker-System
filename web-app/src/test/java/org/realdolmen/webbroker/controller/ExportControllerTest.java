package org.realdolmen.webbroker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.realdolmen.util.EntityFactory;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.TripRepository;
import org.realdolmen.webbroker.xml.XmlSerializer;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.List;

import static org.jgroups.util.Util.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Mock test for the {@link ExportTripController}.
 *
 * @author Youri Flement
 */
@RunWith(MockitoJUnitRunner.class)
public class ExportControllerTest {

    @InjectMocks
    ExportTripController controller;

    @Mock
    TripRepository repository;

    @Mock
    XmlSerializer serializer;

    @Test
    public void canExportAllTrips() throws Exception {
        controller.export(createMockContext());

        verify(serializer, times(1)).marshal(any(), any());
        assertFalse(controller.isUnableToExport());
    }

    @Test
    public void shouldUseUTF8() throws Exception {
        FacesContext mockContext = createMockContext();
        controller.export(mockContext);
        verify(mockContext.getExternalContext()).setResponseCharacterEncoding("UTF-8");
    }

    @Test
    public void canHandleXmlException() throws Exception {
        doThrow(new JAXBException("")).when(serializer).marshal(any(), any());
        controller.export(createMockContext());
        assertTrue(controller.isUnableToExport());
    }

    private List<Trip> createTrips() {
        Trip tripA = EntityFactory.createTrip();
        Trip tripB = EntityFactory.createTrip();
        return Arrays.asList(tripA, tripB);
    }

    private FacesContext createMockContext() {
        FacesContext mockedContext = mock(FacesContext.class);
        ExternalContext mockedExternalContext = mock(ExternalContext.class);

        when(repository.getAllTrips()).thenReturn(createTrips());
        when(mockedContext.getExternalContext()).thenReturn(mockedExternalContext);
        return mockedContext;
    }

}
