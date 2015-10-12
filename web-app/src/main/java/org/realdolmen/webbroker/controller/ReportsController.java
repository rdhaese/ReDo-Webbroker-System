package org.realdolmen.webbroker.controller;


import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.model.Region;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.BookingRepository;
import org.realdolmen.webbroker.repository.RegionRepository;
import org.realdolmen.webbroker.repository.TripRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReportsController {

    private List<Trip> trips;

    private List<Trip> filteredTrips;

    @Inject
    TripRepository tripRepository;

    @Inject
    BookingRepository bookingRepository;

    @Inject
    RegionRepository regionRepository;

    @Inject
    PriceCalcService priceCalcService;

    @PostConstruct
    public void loadBookings() {
        trips = tripRepository.getAllTrips();


        for (Trip trip : trips) {
            List<Booking> bookingsWithTrip = bookingRepository.getBookingsWithTrip(trip);
            int average = 0;
            int max = 0;
            int min = 0;

            for (Booking booking : bookingsWithTrip) {
                average
            }
        }
    }

    public boolean filterByDate(Object value, Object filter, Locale locale) {
        LocalDateTime date = (LocalDateTime) value;
        String parsedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String filterString = (String) filter;
        return parsedDate.contains(filterString);
    }

    public List<String> getRegions() {
        return regionRepository.getAllRegions().stream().map(Region::getName).collect(Collectors.toList());
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Trip> getFilteredTrips() {
        return filteredTrips;
    }

    public void setFilteredTrips(List<Trip> filteredTrips) {
        this.filteredTrips = filteredTrips;
    }
}
