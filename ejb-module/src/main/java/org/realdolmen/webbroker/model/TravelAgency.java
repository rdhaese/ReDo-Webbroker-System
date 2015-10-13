package org.realdolmen.webbroker.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 * Entity representing a travel agency
 * @author Robin D'Haese
 */
@Entity
public class TravelAgency extends BaseEntity{

    @NotNull
    private String name;

    public TravelAgency() {
    }

    public TravelAgency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TravelAgency that = (TravelAgency) o;

        return !(getName() != null ? !getName().equals(that.getName()) : that.getName() != null);

    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
