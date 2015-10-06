package org.realdolmen.webbroker.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 */

@Entity
public class AirlineCompany extends BaseEntity{

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        AirlineCompany that = (AirlineCompany) o;

        return !(getName() != null ? !getName().equals(that.getName()) : that.getName() != null);
    }

}
