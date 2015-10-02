package org.realdolmen.webbroker.model;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class Airport extends BaseEntity {
    private Region region;
    private Address address;
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
