package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.exception.AmbiguousEntityException;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.model.TravelAgency;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.FlightRepository;
import org.realdolmen.webbroker.repository.TravelAgencyRepository;
import org.realdolmen.webbroker.repository.TripRepository;
import org.realdolmen.webbroker.xml.XmlSerializer;
import org.realdolmen.webbroker.xml.element.FlightXmlElement;
import org.realdolmen.webbroker.xml.element.TripXmlElement;
import org.realdolmen.webbroker.xml.element.TripsXmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class ImportTripController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportTripController.class);

    private Part file;

    @Inject
    TripRepository tripRepository;

    @Inject
    TravelAgencyRepository travelAgencyRepository;

    @Inject
    FlightRepository flightRepository;

    @Inject
    XmlSerializer serializer;

    private boolean unableToParse = false;
    private boolean unableToOpen = false;
    private boolean noneSelected = false;
    private boolean agencyIsNull = false;
    private boolean flightNotFound = false;
    private boolean addedSuccess = false;
    private boolean multipleFound = false;

    public void upload() {
        resetFlags();
        try {
            TripsXmlElement tripsXmlElement = serializer.unmarshalStream(TripsXmlElement.class, file.getInputStream());
            tripsXmlElement.getTrips().forEach(this::processTripElement);
        } catch (JAXBException e) {
            unableToParse = true;
            LOGGER.warn("Unable to parse XML file: " + file.getName());
        } catch (IOException e) {
            unableToOpen = true;
            LOGGER.warn("Unable to open XML file: " + file.getName());
        } catch (NullPointerException e) {
            noneSelected = true;
        }
    }

    protected void processTripElement(TripXmlElement tripElement) {
        try {
            TravelAgency agency = travelAgencyRepository.getSingleTravelAgency(tripElement.getTravelAgency());
            if (agency == null) {
                agencyIsNull = true;
            } else {
                FlightXmlElement flight1 = tripElement.getFlight();
                Flight flight = flightRepository.getSingleFlight(flight1.getAirlineCompany(), flight1.getDepartureAirport(), flight1.getArrivalAirport(), flight1.getPrice(), flight1.getAvailableSeats());
                if (flight == null) {
                    flightNotFound = true;
                } else {
                    Trip trip = new Trip(flight, agency, tripElement.getAccommodationPrice(), tripElement.getStartDate(), tripElement.getEndDate());
                    tripRepository.add(trip);
                    addedSuccess = true;
                }
            }
        } catch (AmbiguousEntityException e) {
            multipleFound = true;
        }
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public boolean isMultipleFound() {
        return multipleFound;
    }

    public void setMultipleFound(boolean multipleFound) {
        this.multipleFound = multipleFound;
    }

    public boolean isUnableToParse() {
        return unableToParse;
    }

    public void setUnableToParse(boolean unableToParse) {
        this.unableToParse = unableToParse;
    }

    public boolean isUnableToOpen() {
        return unableToOpen;
    }

    public void setUnableToOpen(boolean unableToOpen) {
        this.unableToOpen = unableToOpen;
    }

    public boolean isNoneSelected() {
        return noneSelected;
    }

    public void setNoneSelected(boolean noneSelected) {
        this.noneSelected = noneSelected;
    }

    public boolean isAgencyIsNull() {
        return agencyIsNull;
    }

    public void setAgencyIsNull(boolean agencyIsNull) {
        this.agencyIsNull = agencyIsNull;
    }

    public boolean isFlightNotFound() {
        return flightNotFound;
    }

    public void setFlightNotFound(boolean flightNotFound) {
        this.flightNotFound = flightNotFound;
    }

    public boolean isAddedSuccess() {
        return addedSuccess;
    }

    public void setAddedSuccess(boolean addedSuccess) {
        this.addedSuccess = addedSuccess;
    }

    private void resetFlags() {
        unableToParse = false;
        unableToOpen = false;
        noneSelected = false;
        agencyIsNull = false;
        flightNotFound = false;
        addedSuccess = false;
        multipleFound = false;
    }
}
