package org.realdolmen.webbroker.model;

import java.io.Serializable;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class BaseEntity implements Serializable {

    private Long id;

    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
