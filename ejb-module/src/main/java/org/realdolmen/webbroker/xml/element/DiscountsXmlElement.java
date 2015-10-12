package org.realdolmen.webbroker.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by RDEAX37 on 10/10/2015.
 */
@XmlRootElement(name = "discounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class DiscountsXmlElement {

    @XmlElement(name = "discount")
    private List<DiscountXmlElement> discounts = null;

    public List<DiscountXmlElement> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountXmlElement> discounts) {
        this.discounts = discounts;
    }

}
