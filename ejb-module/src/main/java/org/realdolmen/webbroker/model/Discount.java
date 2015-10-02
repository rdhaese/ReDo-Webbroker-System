package org.realdolmen.webbroker.model;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class Discount extends BaseEntity{
    private String name;
    private Double quantity;
    private Boolean percentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Boolean getPercentage() {
        return percentage;
    }

    public void setPercentage(Boolean percentage) {
        this.percentage = percentage;
    }
}
