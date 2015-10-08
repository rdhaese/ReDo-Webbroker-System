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

@Named
@RequestScoped
public class ExportTripController implements Serializable {

    @Inject
    TripRepository tripRepository;

    @Inject
    XmlSerializer serializer;

    private String message = "";

    public void export(FacesContext context) {
        ExternalContext ec = context.getExternalContext();

        ec.responseReset();
        ec.setResponseContentType("application/xml");
        ec.setResponseCharacterEncoding("UTF-8");
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "allTrips.xml" + "\"");
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

    public void export() {
        export(FacesContext.getCurrentInstance());
    }

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

    private TripXmlElement tripToXmlElement(Trip trip) {
        TripXmlElement element = new TripXmlElement();
        element.setAccommodationPrice(trip.getAccommodationPrice());
        element.setFlight(flightToXmlElement(trip.getFlight()));
        element.setStartDate(trip.getStartDate());
        element.setEndDate(trip.getEndDate());
        element.setTravelAgency(trip.getTravelAgency().getName());
        return element;
    }

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
