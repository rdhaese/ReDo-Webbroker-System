package org.realdolmen.webbroker.model.user;

import org.realdolmen.webbroker.model.AirlineCompany;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@Entity
public class AirlineCompanyEmployee extends User {

    @NotNull
    @ManyToOne
    private AirlineCompany company;

    public AirlineCompany getCompany() {
        return company;
    }

    public void setCompany(AirlineCompany company) {
        this.company = company;
    }
}
