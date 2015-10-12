package org.realdolmen.webbroker.controller;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.LatLng;
import org.realdolmen.webbroker.model.Airport;
import org.realdolmen.webbroker.repository.AirportRepository;
import org.realdolmen.webbroker.repository.RegionRepository;
import org.realdolmen.webbroker.service.LocationService;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@javax.faces.view.ViewScoped
public class SearchDestinationController implements Serializable {

    @Inject
    RegionRepository repository;

    @Inject
    LocationService locationService;

    @Inject
    AirportRepository airportRepository;

    private String selectedRegion = "";

    private List<Airport> availableDestinations = new ArrayList<>();

    private Long selectedDestination = -1L;

    public void onPointSelect(PointSelectEvent event) {
        LatLng latLng = event.getLatLng();
        String countryCode = locationService.latLongToCountryCode(latLng.getLat(), latLng.getLng());
        selectedRegion = locationService.countryCodeToContinent(countryCode);
        availableDestinations = airportRepository.getAirportsInContinent(selectedRegion);
    }

    public Long getSelectedDestination() {
        return selectedDestination;
    }

    public void setSelectedDestination(Long selectedDestination) {
        this.selectedDestination = selectedDestination;
    }

    public List<Airport> getAvailableDestinations() {
        return availableDestinations;
    }

    public void setAvailableDestinations(List<Airport> availableDestinations) {
        this.availableDestinations = availableDestinations;
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String selectedRegion) {
        this.selectedRegion = selectedRegion;
    }
}
