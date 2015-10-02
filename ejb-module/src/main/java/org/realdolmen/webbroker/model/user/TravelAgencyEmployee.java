package org.realdolmen.webbroker.model.user;

import org.realdolmen.webbroker.model.TravelAgency;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class TravelAgencyEmployee extends User {
    private TravelAgency travelAgency;

    public TravelAgency getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }
}
