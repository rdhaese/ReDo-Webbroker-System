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
    private List<PageXmlElement> pages = null;

    public List<PageXmlElement> getPages() {
        return pages;
    }

    public void setPages(List<PageXmlElement> trips) {
        this.pages = trips;
    }
}
