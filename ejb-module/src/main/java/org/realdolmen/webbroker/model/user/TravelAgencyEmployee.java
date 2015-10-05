package org.realdolmen.webbroker.model.user;

import org.realdolmen.webbroker.model.TravelAgency;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@Entity
public class TravelAgencyEmployee extends User {

    @NotNull
    @ManyToOne
    private TravelAgency travelAgency;

    public TravelAgency getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }
}
