package org.realdolmen.webbroker.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by YFMAX32 on 7/10/2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trips")
public class TripsXmlElement {

    @XmlElement(name = "trip")
    private List<TripXmlElement> trips = null;

    public List<TripXmlElement> getTrips() {
        return trips;
    }

    public void setTrips(List<TripXmlElement> trips) {
        this.trips = trips;
    }
}
