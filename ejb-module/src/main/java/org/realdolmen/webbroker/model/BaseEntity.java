package org.realdolmen.webbroker.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
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
