package org.realdolmen.webbroker.controller;

import org.primefaces.context.RequestContext;
import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.repository.AirportRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by RDEAX37 on 7/10/2015.
 *
 * Controller for the search trip functionality.
 */
@Named
@RequestScoped
public class SearchTripController {

    @Inject
    private AirportRepository airportRepo;

    @NotNull (message = "Please select a destination.")
    private Long destination_id;
    @NotNull (message = "Please enter a departure date.")
    private Date departureDate;
    @NotNull (message = "Please enter an arrival date.")
    private Date arrivalDate;
    @NotNull (message = "Please enter the number of persons.")
    @DecimalMin( value = "1", message = "Please provide a number larger than 0.")
    private Integer numberOfPersons;

    /**
     * Collects all trips fitting the given constraints.
     * @return the next page to navigate to -> found-trips
     */
    public String searchTrip(){
        //TODO
        return "found-trips";
    }

    /**
     * @return All airports from the persistence context.
     */
    public List<Airport> getAirports(){
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
}
