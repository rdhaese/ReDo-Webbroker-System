package org.realdolmen.webbroker.model;

import java.time.Period;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class Trip extends BaseEntity{
    private Flight flight;
    private TravelAgency travelAgency;
    private Double accommodation;
    private Period period;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public TravelAgency getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    public Double getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Double accommodation) {
        this.accommodation = accommodation;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
