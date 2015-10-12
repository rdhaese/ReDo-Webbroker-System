package org.realdolmen.webbroker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 * Entity representing an airport
 * @author Robin D'Haese
 */
@Entity
public class Airport extends BaseEntity {
    @NotNull
    @OneToOne (cascade = CascadeType.ALL)
    private Region region;
    @NotNull
    @OneToOne (cascade = CascadeType.ALL)
    private Address address;
    @NotNull
    private String name;

    public Airport() {
    }

    public Airport(String name, Region region, Address address) {
        this.name = name;
        this.region = region;
        this.address = address;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        if (!(o instanceof Airport)) return false;

        Airport airport = (Airport) o;

        if (getRegion() != null ? !getRegion().equals(airport.getRegion()) : airport.getRegion() != null) return false;
        if (getAddress() != null ? !getAddress().equals(airport.getAddress()) : airport.getAddress() != null)
            return false;
        return !(getName() != null ? !getName().equals(airport.getName()) : airport.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getRegion() != null ? getRegion().hashCode() : 0;
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return name;
    }
}
