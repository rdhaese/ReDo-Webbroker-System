package org.realdolmen.webbroker.controller;


import org.realdolmen.webbroker.util.TripReport;
import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.model.Region;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.BookingRepository;
import org.realdolmen.webbroker.repository.RegionRepository;
import org.realdolmen.webbroker.repository.TripRepository;
import org.realdolmen.webbroker.service.PriceCalcService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReportsController {

    private List<TripReport> trips;

    private List<TripReport> filteredTrips;

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
        trips = new ArrayList<>();
        filteredTrips = new ArrayList<>();
        List<Trip> allTrips = tripRepository.getAllTrips();

        for (Trip trip : allTrips) {
            List<Booking> bookingsWithTrip = bookingRepository.getBookingsWithTrip(trip);
            double average = 0;
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;

            for (Booking booking : bookingsWithTrip) {
                Double bookingPrice = priceCalcService.getTotalBookingPriceWithDiscount(booking);
                average += bookingPrice;
                if(bookingPrice > max) {
                    max = bookingPrice;
                }
                if(bookingPrice < min) {
                    min = bookingPrice;
                }
            }
            average = average / bookingsWithTrip.size();

            TripReport report = new TripReport(trip, min, max, average);
            trips.add(report);
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

    public List<TripReport> getTrips() {
        return trips;
    }

    public void setTrips(List<TripReport> trips) {
        this.trips = trips;
    }

    public List<TripReport> getFilteredTrips() {
        return filteredTrips;
    }

    public void setFilteredTrips(List<TripReport> filteredTrips) {
        this.filteredTrips = filteredTrips;
    }
}
