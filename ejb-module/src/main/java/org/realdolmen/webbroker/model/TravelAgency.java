package org.realdolmen.webbroker.model;

import javax.persistence.Entity;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@Entity
public class TravelAgency extends BaseEntity{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
