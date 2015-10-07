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
import java.util.List;

/**
 * Created by RDEAX37 on 6/10/2015.
 */
@Named
@RequestScoped
public class AddFlightController {

    @Inject
    private AirportRepository airportRepo;
    @Inject
    private FlightRepository flightRepo;
    @Inject
    private LoggedInUserController loggedInUserController;

    @NotNull(message = "Departure airport should be picked.")
    private Long departure_id;
    @NotNull(message = "Arrival airport should be picked.")
    private Long arrival_id;
    @NotNull(message = "Price must be filled in.")
    @Min(value = 0, message = "Price can't be lower than 0.")
    private Double price;
    @NotNull(message = "Amount of seats must be filled in.")
    @Min(value = 1, message = "Amount of seats can't be lower than 1.")
    private Integer amountOfSeats;

    private String message;

    public String addFlight() {
        if (departure_id == arrival_id){
            message = "Departure and arrival can't be the same";
            return "add-flight";
        }
        try {
            flightRepo.add(createFlight());
        } catch (Exception e) {
            message = "Something went wrong while adding the flight.";
            return "add-flight";
        }
        message = "Flight added successfully.";
        clearFormState();
        return "add-flight";
    }

    private void clearFormState() {
        departure_id = null;
        arrival_id = null;
        price = null;
        amountOfSeats = null;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
