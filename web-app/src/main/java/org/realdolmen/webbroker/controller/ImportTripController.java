package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.exception.AmbiguousEntityException;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.model.TravelAgency;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.FlightRepository;
import org.realdolmen.webbroker.repository.TravelAgencyRepository;
import org.realdolmen.webbroker.repository.TripRepository;
import org.realdolmen.webbroker.xml.element.FlightXmlElement;
import org.realdolmen.webbroker.xml.element.TripXmlElement;
import org.realdolmen.webbroker.xml.element.TripsXmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ManagedBean
@ViewScoped
public class ImportTripController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportTripController.class);

    private Part file;

    @Inject
    TripRepository tripRepository;

    @Inject
    TravelAgencyRepository travelAgencyRepository;

    @Inject
    FlightRepository flightRepository;

    private String message;

    private boolean isError;

    public void upload() {
        try {
            TripsXmlElement tripsXmlElement = parseXml(TripsXmlElement.class, file.getInputStream());
            tripsXmlElement.getTrips().forEach(this::processTripElement);
        } catch (JAXBException e) {
            message = "Unable to parse XML file.";
            LOGGER.warn(message + ": " + file.getName());
        } catch (IOException e) {
            message = "Unable to open XML file.";
            LOGGER.warn(message + ": " + file.getName());
        }
    }

    private boolean processTripElement(TripXmlElement tripElement) {
        try {
            TravelAgency agency = findTravelAgency(tripElement.getTravelAgency());
            if (agency == null) {
                return errorMessage("Travel agency '" + tripElement.getTravelAgency() + "' was not found.");
            }

            FlightXmlElement flight1 = tripElement.getFlight();
            Flight flight = findFlight(flight1.getAirlineCompany(), flight1.getDepartureAirport(), flight1.getArrivalAirport(), flight1.getPrice(), flight1.getAvailableSeats());
            if (flight == null) {
                return errorMessage("Flight between " + flight1.getArrivalAirport() + " and " + flight1.getDepartureAirport() + " was not found.");
            }

            Trip trip = new Trip(flight, agency, tripElement.getAccommodationPrice(), tripElement.getStartDate(), tripElement.getEndDate());
            tripRepository.add(trip);
            return successMessage("Trips were successfully added.");
        } catch (AmbiguousEntityException e) {
            return errorMessage("Multiple flights or travel agencies were found for the input flights/travel agencies.");
        }
    }

    private boolean successMessage(String message) {
        this.message = message;
        this.isError = false;
        return false;
    }

    private boolean uploadSuccessful() {
        return !isError;
    }

    public boolean errorMessage(String message) {
        this.message = message;
        this.isError = true;
        return true;
    }

    // TODO: not here
    private <T> T parseXml(Class<T> tClass, InputStream source) throws JAXBException {
        return parseXml(tClass, new StreamSource(source));
    }

    private <T> T parseXml(Class<T> tClass, Source source) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(source, tClass).getValue();
    }

    // TODO: dit hoort hier wss niet
    private TravelAgency findTravelAgency(String name) throws AmbiguousEntityException {
        List<TravelAgency> agencies = travelAgencyRepository.getTravelAgenciesByName(name);
        if (agencies.isEmpty()) {
            return null;
        } else if (agencies.size() > 1) {
            throw new AmbiguousEntityException(agencies.size() + " travel agencies with name '" + name + "' were found.");
        } else {
            return agencies.get(0);
        }
    }

    private Flight findFlight(String company, String departure, String arrival, Double price, Integer availableSeats) {
        List<Flight> flights = flightRepository.findFlight(company, departure, arrival, price, availableSeats);
        if (flights.isEmpty()) {
            return null;
        } else if (flights.size() > 1) {
            throw new AmbiguousEntityException(flights.size() + " flights found for parameters: " + company + "," + departure + "," + arrival + "," + price + "," + availableSeats);
        }
        return flights.get(0);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
