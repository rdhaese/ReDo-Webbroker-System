package org.realdolmen.webbroker.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by RDEAX37 on 10/10/2015.
*/
 @XmlAccessorType(XmlAccessType.FIELD)
 @XmlRootElement(name = "discount")
public class DiscountXmlElement {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected boolean isPercentage;
    @XmlElement(required = true)
    protected double quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPercentage() {
        return isPercentage;
    }

    public void setIsPercentage(boolean isPercentage) {
        this.isPercentage = isPercentage;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
