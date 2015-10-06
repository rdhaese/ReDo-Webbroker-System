package org.realdolmen.webbroker.model;

import org.realdolmen.webbroker.converter.LocalDateTimePersistenceConverter;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Entity which represents a trip.
 *
 * @author Youri Flement
 */
@Entity
public class Trip extends BaseEntity {

    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private Flight flight;

    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private TravelAgency travelAgency;

    @NotNull
    @Min(value = 0)
    private Double accommodationPrice;

    @NotNull
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime startDate;

    @NotNull
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime endDate;

    public Trip() {
    }

    public Trip(Flight flight, TravelAgency travelAgency, Double accommodationPrice, LocalDateTime startDate, LocalDateTime endDate) {
        this.flight = flight;
        this.travelAgency = travelAgency;
        this.accommodationPrice = accommodationPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

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

    public Double getAccommodationPrice() {
        return accommodationPrice;
    }

    public void setAccommodationPrice(Double accommodation) {
        if(accommodation != null && accommodation < 0) {
            throw new IllegalArgumentException();
        }
        this.accommodationPrice = accommodation;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
