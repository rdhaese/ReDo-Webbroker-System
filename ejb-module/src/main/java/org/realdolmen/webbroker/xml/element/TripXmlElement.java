package org.realdolmen.webbroker.xml.element;

import org.realdolmen.webbroker.xml.adapter.LocalDateTimeXmlAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trip")
public class TripXmlElement {

    @XmlElement(name = "flight")
    private FlightXmlElement flight;

    @XmlElement(name = "travelagency")
    private String travelAgency;

    @XmlElement(name = "accommodationPrice")
    private Double accommodationPrice;

    @XmlElement(name = "startDate")
    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDateTimeXmlAdapter.class)
    private LocalDateTime startDate;

    @XmlElement(name = "endDate")
    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDateTimeXmlAdapter.class)
    private LocalDateTime endDate;

    public FlightXmlElement getFlight() {
        return flight;
    }

    public void setFlight(FlightXmlElement flight) {
        this.flight = flight;
    }

    public String getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(String travelAgency) {
        this.travelAgency = travelAgency;
    }

    public Double getAccommodationPrice() {
        return accommodationPrice;
    }

    public void setAccommodationPrice(Double accommodationPrice) {
        this.accommodationPrice = accommodationPrice;
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
