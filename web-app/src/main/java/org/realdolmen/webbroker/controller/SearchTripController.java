package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.AirportRepository;
import org.realdolmen.webbroker.repository.TripRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @NotNull
    private Long destination_id;

    @NotNull
    private Date departureDate;

    @NotNull
    private Date arrivalDate;

    @NotNull
    @DecimalMin(value = "1")
    private Integer numberOfPersons;

    private List<Trip> foundTrips = new ArrayList<>();

    private boolean performedASearch = false;

    public boolean isPerformedASearch() {
        return performedASearch;
    }

    public void setPerformedASearch(boolean performedASearch) {
        this.performedASearch = performedASearch;
    }

    public List<Trip> getFoundTrips() {
        return foundTrips;
    }

    public void setFoundTrips(List<Trip> foundTrips) {
        this.foundTrips = foundTrips;
    }

    public void findTrips() {
        Airport destination = airportRepo.find(destination_id);
        LocalDate depDate = new java.sql.Date(departureDate.getTime()).toLocalDate();
        LocalDate arrDate = new java.sql.Date(arrivalDate.getTime()).toLocalDate();
        foundTrips = tripRepo.searchTrips(destination, depDate, arrDate, numberOfPersons);
        performedASearch = true;
    }

    public List<Airport> getAirports() {
        System.out.println(destination_id);
        return airportRepo.getAllAirports();
    }

    public String test(Long id) {
        this.destination_id = id;

        return "search-trip";
    }

    public void test() {
        Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("myparam");
        System.out.println(action);

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
