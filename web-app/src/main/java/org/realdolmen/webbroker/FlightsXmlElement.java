package org.realdolmen.webbroker;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by YFMAX32 on 7/10/2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "flights")
public class FlightsXmlElement {

    @XmlElement(name = "flight")
    private List<FlightXmlElement> flights;

    public List<FlightXmlElement> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightXmlElement> flights) {
        this.flights = flights;
    }
}
