package org.realdolmen.webbroker.xml.element;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "page")
public class PageXmlElement {

    @XmlAttribute
    private String path;

    @XmlElement(name = "roles")
    private RolesXmlElement roles;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RolesXmlElement getRoles() {
        return roles;
    }

    public void setRoles(RolesXmlElement roles) {
        this.roles = roles;
    }
}
