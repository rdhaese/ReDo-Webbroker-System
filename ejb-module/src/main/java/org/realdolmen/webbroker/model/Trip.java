package org.realdolmen.webbroker.model;

import org.realdolmen.webbroker.converter.LocalDateTimePersistenceConverter;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

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

    /**
     * @return the accommodationPrice multiplied with the amountOfDays
     */
    public Double getTotalAccommodationPrice() {
        return accommodationPrice * getAmountOfDays();
    }

    /**
     * @return The difference in days between startDate and endDate
     */
    public int getAmountOfDays() {
        return Period.between(startDate.toLocalDate(), endDate.toLocalDate()).getDays();
    }

    /**
     * @return the startDate, but converted to java.util.Date
     */
    public Date getStartDateInOldApi() {
        return Date.from(startDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @return the endDate, but converted to java.util.Date
     */
    public Date getEndDateInOldApi() {
        return Date.from(endDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
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
        if (accommodation != null && accommodation < 0) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (getFlight() != null ? !getFlight().equals(trip.getFlight()) : trip.getFlight() != null) return false;
        if (getTravelAgency() != null ? !getTravelAgency().equals(trip.getTravelAgency()) : trip.getTravelAgency() != null)
            return false;
        if (getAccommodationPrice() != null ? !getAccommodationPrice().equals(trip.getAccommodationPrice()) : trip.getAccommodationPrice() != null)
            return false;
        if (getStartDate() != null ? !getStartDate().equals(trip.getStartDate()) : trip.getStartDate() != null)
            return false;
        return !(getEndDate() != null ? !getEndDate().equals(trip.getEndDate()) : trip.getEndDate() != null);

    }

    @Override
    public int hashCode() {
        int result = getFlight() != null ? getFlight().hashCode() : 0;
        result = 31 * result + (getTravelAgency() != null ? getTravelAgency().hashCode() : 0);
        result = 31 * result + (getAccommodationPrice() != null ? getAccommodationPrice().hashCode() : 0);
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        return result;
    }


}
