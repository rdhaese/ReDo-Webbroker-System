package org.realdolmen.webbroker;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersXmlElement {

    @XmlElement(name = "user")
    private List<UserXmlElement> users = null;

    public List<UserXmlElement> getUsers() {
        return users;
    }

    public void setUsers(List<UserXmlElement> users) {
        this.users = users;
    }

}
