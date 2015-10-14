package org.realdolmen.webbroker.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 * Entity representing an airline company
 * @author Robin D'Haese
 */
@Entity
public class AirlineCompany extends BaseEntity{

    @NotNull
    private String name;

    public AirlineCompany() {
    }

    public AirlineCompany(String name) {
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

        AirlineCompany that = (AirlineCompany) o;

        return !(getName() != null ? !getName().equals(that.getName()) : that.getName() != null);

    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
