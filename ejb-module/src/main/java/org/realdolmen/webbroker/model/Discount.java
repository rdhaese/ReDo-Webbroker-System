package org.realdolmen.webbroker.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entity which represents the various discounts.
 *
 * @author Youri Flement
 */
@Entity
public class Discount extends BaseEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(Discount.class);

    @NotNull
    private String name;

    @NotNull
    @Min(value = 0)
    private Double quantity;

    @NotNull
    private Boolean isPercentage;

    public Discount() {
    }

    public Discount(String name, Double quantity, Boolean isPercentage) {
        this.name = name;
        this.quantity = quantity;
        this.isPercentage = isPercentage;
    }

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
        if(quantity != null && quantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.quantity = quantity;
    }

    public Boolean isPercentage() {
        return isPercentage;
    }

    public void setIsPercentage(Boolean isPercentage) {
        this.isPercentage = isPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (getName() != null ? !getName().equals(discount.getName()) : discount.getName() != null) return false;
        if (getQuantity() != null ? !getQuantity().equals(discount.getQuantity()) : discount.getQuantity() != null)
            return false;
        return !(isPercentage != null ? !isPercentage.equals(discount.isPercentage) : discount.isPercentage != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getQuantity() != null ? getQuantity().hashCode() : 0);
        result = 31 * result + (isPercentage != null ? isPercentage.hashCode() : 0);
        return result;
    }
}
