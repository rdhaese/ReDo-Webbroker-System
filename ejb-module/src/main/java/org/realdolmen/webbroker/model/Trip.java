package org.realdolmen.webbroker.model;

import org.realdolmen.webbroker.converter.LocalDateTimePersistenceConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Entity which represents a trip.
 *
 * @author Youri Flement
 */
@Entity
public class Trip extends BaseEntity {

    @OneToOne
    @NotNull
    private Flight flight;

    @OneToOne
    @NotNull
    private TravelAgency travelAgency;

    @NotNull
    private Double accommodationPrice;

    @NotNull
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime startDate;

    @NotNull
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime endDate;

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
