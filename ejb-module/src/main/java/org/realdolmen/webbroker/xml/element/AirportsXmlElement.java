package org.realdolmen.webbroker.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "airports")
@XmlAccessorType(XmlAccessType.FIELD)
public class AirportsXmlElement {

    @XmlElement(name = "airport")
    private List<AirportXmlElement> airports = null;

    public List<AirportXmlElement> getAirports() {
        return airports;
    }

    public void setAirports(List<AirportXmlElement> airports) {
        this.airports = airports;
    }

}
