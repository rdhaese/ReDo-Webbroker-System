package org.realdolmen.webbroker;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "regions")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegionsXmlElement {

    @XmlElement(name = "region")
    private List<RegionXmlElement> regions = null;

    public List<RegionXmlElement> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionXmlElement> regions) {
        this.regions = regions;
    }
}
