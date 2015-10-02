package org.realdolmen.webbroker.model.user;

import org.realdolmen.webbroker.model.AirlineCompany;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class AirlineCompanyEmployee extends User {
    public AirlineCompany getCompany() {
        return company;
    }

    public void setCompany(AirlineCompany company) {
        this.company = company;
    }

    private AirlineCompany company;
}
