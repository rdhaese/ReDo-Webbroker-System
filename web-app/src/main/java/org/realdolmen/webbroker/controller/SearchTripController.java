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

    public String searchTrip(){

        return "found-trips";
    }


    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

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
