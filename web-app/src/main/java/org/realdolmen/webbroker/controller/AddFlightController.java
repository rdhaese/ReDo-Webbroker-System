package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.AirlineCompany;
import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.model.user.AirlineCompanyEmployee;
import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repository.AirportRepository;
import org.realdolmen.webbroker.repository.FlightRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by RDEAX37 on 6/10/2015.
 * Controller to add flights.
 * @author Robin D'Haese
 */
@Named
@RequestScoped
public class AddFlightController implements Serializable {

    @Inject
    private AirportRepository airportRepo;
    @Inject
    private FlightRepository flightRepo;
    @Inject
    private LoggedInUserController loggedInUserController;

    @NotNull
    private Long departure_id;
    @NotNull
    private Long arrival_id;
    @NotNull
    @Min(value = 0)
    private Double price;
    @NotNull
    @Min(value = 1)
    private Integer amountOfSeats;

    private boolean depAndArrTheSame = false;
    private boolean error = false;
    private boolean success = false ;

    /**
     * Adds a flight if possible.
     * If departure and arrival are the same or if adding to the persistence context fails, a message is set and no flight is added, form data is kept.
     * If a flight is added successfully, a corresponding message is set and the form state is cleared.
     * @return the next page to navigate to
     */
    public String addFlight() {
        if (departure_id == arrival_id){
            depAndArrTheSame = true;
            return "add-flight";
        }
        try {
            flightRepo.add(createFlight());

        } catch (Exception e) {
            error = true;
            return "add-flight";
        }
        success = true;
        clearFormState();
        return "add-flight";
    }

    /**
     * Clears the form state by setting all properties to null
     */
    private void clearFormState() {
        departure_id = null;
        arrival_id = null;
        price = null;
        amountOfSeats = null;
    }

    /**
     * Creates a flight with the data from the properties and the logged in user.
     * @return the created flight
     */
    private Flight createFlight() {
        Flight flight = new Flight();
        flight.setDeparture(airportRepo.find(departure_id));
        flight.setArrival(airportRepo.find(arrival_id));
        flight.setPrice(price);
        flight.setAvailableSeats(amountOfSeats);

        User loggedInUser = loggedInUserController.getLoggedInUser();
        AirlineCompanyEmployee ace = (AirlineCompanyEmployee) loggedInUser;
        flight.setCompany(ace.getCompany());
        return flight;
    }

    public List<Airport> getAirports() {
        return airportRepo.getAllAirports();
    }

    public boolean isDepAndArrTheSame() {
        return depAndArrTheSame;
    }

    public void setDepAndArrTheSame(boolean depAndArrTheSame) {
        this.depAndArrTheSame = depAndArrTheSame;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getDeparture_id() {
        return departure_id;
    }

    public void setDeparture_id(Long departure_id) {
        this.departure_id = departure_id;
    }

    public Long getArrival_id() {
        return arrival_id;
    }

    public void setArrival_id(Long arrival_id) {
        this.arrival_id = arrival_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(Integer amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }
}
