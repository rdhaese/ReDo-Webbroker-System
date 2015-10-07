package org.realdolmen.webbroker.model.user;

import org.realdolmen.webbroker.model.TravelAgency;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@Entity
public class TravelAgencyEmployee extends User {

    @NotNull
    @ManyToOne (cascade = CascadeType.PERSIST)
    private TravelAgency travelAgency;

    public TravelAgency getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TravelAgencyEmployee that = (TravelAgencyEmployee) o;

        return !(getTravelAgency() != null ? !getTravelAgency().equals(that.getTravelAgency()) : that.getTravelAgency() != null);

    }

    @Override
    public int hashCode() {
        return getTravelAgency() != null ? getTravelAgency().hashCode() : 0;
    }
}
