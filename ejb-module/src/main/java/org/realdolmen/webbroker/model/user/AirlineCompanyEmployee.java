package org.realdolmen.webbroker.model.user;

import org.realdolmen.webbroker.model.AirlineCompany;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 *
 * Entity representing a User that works for an AirlineCompany
 * @author Robin D'Haese
 */
@Entity
public class AirlineCompanyEmployee extends User {

    @NotNull
    @ManyToOne (cascade = CascadeType.PERSIST)
    private AirlineCompany company;

    public AirlineCompany getCompany() {
        return company;
    }

    public void setCompany(AirlineCompany company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirlineCompanyEmployee that = (AirlineCompanyEmployee) o;

        return !(getCompany() != null ? !getCompany().equals(that.getCompany()) : that.getCompany() != null);

    }

    @Override
    public int hashCode() {
        return getCompany() != null ? getCompany().hashCode() : 0;
    }
}
