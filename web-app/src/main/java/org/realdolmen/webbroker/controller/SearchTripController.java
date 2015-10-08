package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.AirportRepository;
import org.realdolmen.webbroker.repository.TripRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RDEAX37 on 7/10/2015.
 * <p>
 * Controller for the search trip functionality.
 */
@Named
@SessionScoped
public class SearchTripController implements Serializable {

    @Inject
    private AirportRepository airportRepo;
    @Inject
    private TripRepository tripRepo;

    @NotNull(message = "Please select a destination.")
    private Long destination_id;
    @NotNull(message = "Please enter a departure date.")
    private Date departureDate;
    @NotNull(message = "Please enter an arrival date.")
    private Date arrivalDate;
    @NotNull(message = "Please enter the number of persons.")
    @DecimalMin(value = "1", message = "Please provide a number larger than 0.")
    private Integer numberOfPersons;

    private String errorMessage;
    private List<Trip> foundTrips = new ArrayList<Trip>();


    /**
     * Collects all trips fitting the given constraints.
     *
     * @return the next page to navigate to -> found-trips; if no trips are found -> a message is set and the form is shown again.
     */
    public String searchTrip() {
        fillFoundTrips();
        if (foundTrips.size() == 0) {
            errorMessage = "No trips found";
            return "search-trips";
        }
        return "found-trips";
    }

    /**
     * Gets all trips from the repo with the given arguments.
     */
    private void fillFoundTrips() {
        Airport destination = airportRepo.find(destination_id);
        LocalDate depDate = new java.sql.Date(departureDate.getTime()).toLocalDate();
        LocalDate arrDate = new java.sql.Date(arrivalDate.getTime()).toLocalDate();
        foundTrips = tripRepo.searchTrips(destination, depDate, arrDate, numberOfPersons);
    }

    /**
     * @return All airports from the persistence context.
     */
    public List<Airport> getAirports() {
        return airportRepo.getAllAirports();
    }

    public Long getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(Long destination_id) {
        this.destination_id = destination_id;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Trip> getFoundTrips() {
        return foundTrips;
    }

    public void setFoundTrips(List<Trip> foundTrips) {
        this.foundTrips = foundTrips;
    }

}
