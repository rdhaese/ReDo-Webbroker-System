package org.realdolmen.webbroker.util;

import org.realdolmen.webbroker.model.Trip;

import javax.ejb.LocalBean;
import java.io.Serializable;

@LocalBean
public class TripReport implements Serializable {

    private Trip trip;

    private Double min;

    private Double max;

    private Double average;

    public TripReport() {
    }

    public TripReport(Trip trip, Double min, Double max, Double average) {
        this.trip = trip;
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
