package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.TripRepository;
import org.realdolmen.webbroker.xml.XmlSerializer;
import org.realdolmen.webbroker.xml.element.FlightXmlElement;
import org.realdolmen.webbroker.xml.element.TripXmlElement;
import org.realdolmen.webbroker.xml.element.TripsXmlElement;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Controller to export all {@link Trip}s to XML.
 *
 * @author Youri Flement
 */
@Named
@RequestScoped
public class ExportTripController implements Serializable {

    private static final String DEFAULT_FILE_NAME = "allTrips.xml";

    @Inject
    TripRepository tripRepository;

    @Inject
    XmlSerializer serializer;

    private String message = "";

    /**
     * Export all trips from the database to an XML file.
     *
     * @param context The faces context to export from.
     */
    public void export(FacesContext context) {
        ExternalContext ec = context.getExternalContext();

        ec.responseReset();
        ec.setResponseContentType("application/xml");
        ec.setResponseCharacterEncoding("UTF-8");
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + DEFAULT_FILE_NAME + "\"");
        try {
            OutputStream output = ec.getResponseOutputStream();
            Collection<Trip> allTrips = tripRepository.getAllTrips();
            TripsXmlElement xmlTrips = tripsToXmlElement(allTrips);
            serializer.marshal(xmlTrips, output);
        } catch (JAXBException | IOException e) {
            message = "Unable to export the trips as this time.";
        }

        context.responseComplete();
    }

    /**
     * Export all trips with the current {@link FacesContext}.
     */
    public void export() {
        export(FacesContext.getCurrentInstance());
    }

    /**
     * Convert a the collection of trips to their counterpart XML elements.
     *
     * @param trips The trips to convert.
     * @return The trips in their XML representation.
     */
    private TripsXmlElement tripsToXmlElement(Collection<Trip> trips) {
        TripsXmlElement elements = new TripsXmlElement();
        List<TripXmlElement> tripElementList = new ArrayList<>();
        for (Trip trip : trips) {
            TripXmlElement tripElement = tripToXmlElement(trip);
            tripElementList.add(tripElement);
        }

        elements.setTrips(tripElementList);
        return elements;
    }

    /**
     * Convert a {@link Trip} to its counterpart {@link TripXmlElement}.
     *
     * @param trip the trip to convert.
     * @return The converted trip.
     */
    private TripXmlElement tripToXmlElement(Trip trip) {
        TripXmlElement element = new TripXmlElement();
        element.setAccommodationPrice(trip.getAccommodationPrice());
        element.setFlight(flightToXmlElement(trip.getFlight()));
        element.setStartDate(trip.getStartDate());
        element.setEndDate(trip.getEndDate());
        element.setTravelAgency(trip.getTravelAgency().getName());
        return element;
    }

    /**
     * Convert a {@link Flight} to its counterpart {@link FlightXmlElement}.
     *
     * @param flight The flight to convert.
     * @return The converted flight.
     */
    private FlightXmlElement flightToXmlElement(Flight flight) {
        FlightXmlElement element = new FlightXmlElement();
        element.setPrice(flight.getPrice());
        element.setAvailableSeats(flight.getAvailableSeats());
        element.setAirlineCompany(flight.getCompany().getName());
        element.setArrivalAirport(flight.getArrival().getName());
        element.setDepartureAirport(flight.getDeparture().getName());
        return element;
    }

    public String getMessage() {
        return message;
    }

}
