package org.realdolmen.webbroker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@Entity
public class Airport extends BaseEntity {
    @NotNull
    @OneToOne (cascade = CascadeType.PERSIST)
    private Region region;
    @NotNull
    @OneToOne (cascade = CascadeType.PERSIST)
    private Address address;
    @NotNull
    private String name;

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
}
