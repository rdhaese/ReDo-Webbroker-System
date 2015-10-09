package org.realdolmen.webbroker.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by YFMAX32 on 9/10/2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pages")
public class PagesXmlElement {

    @XmlElement(name = "page")
    private List<PageXmlElement> trips = null;

    public List<PageXmlElement> getTrips() {
        return trips;
    }

    public void setTrips(List<PageXmlElement> trips) {
        this.trips = trips;
    }
}
