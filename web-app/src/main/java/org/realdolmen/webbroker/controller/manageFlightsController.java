package org.realdolmen.webbroker.controller;

import org.primefaces.event.RowEditEvent;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.repository.FlightRepository;
import org.realdolmen.webbroker.service.PriceCalcService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by RDEAX37 on 12/10/2015.
 */

@RequestScoped
@Named
public class ManageFlightsController implements Serializable {

    @Inject
    private FlightRepository flightRepository;
    @Inject
    private PriceCalcService priceCalcService;

    private List<Flight> allFlights;


    @PostConstruct
    public void initFlights(){
        allFlights = flightRepository.getAllFlights();
    }

    public void onRowEdit(RowEditEvent event) {
        Flight flight = (Flight) event.getObject();
        flightRepository.updateFlight(flight);
    }

    public List<Flight> getAllFlights() {
        return allFlights;
    }

    public Double getTotalPriceFor(Flight flight){
        return priceCalcService.getTotalFlightPrice(flight);
    }
}
