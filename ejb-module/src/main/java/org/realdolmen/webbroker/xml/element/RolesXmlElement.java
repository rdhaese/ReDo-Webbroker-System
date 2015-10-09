package org.realdolmen.webbroker.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by YFMAX32 on 9/10/2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "roles")
public class RolesXmlElement {

    @XmlElement(name = "role")
    private List<RoleXmlElement> roles;

    public List<RoleXmlElement> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleXmlElement> roles) {
        this.roles = roles;
    }
}
