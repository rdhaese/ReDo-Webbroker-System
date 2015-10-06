package org.realdolmen.webbroker.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *  Class to represent the region of something.
 *
 * @author Youri Flement
 */
@Entity
public class Region extends BaseEntity {

    @NotNull
    private String name;

    @NotNull
    private String code;

    public Region() {
    }

    public Region(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
