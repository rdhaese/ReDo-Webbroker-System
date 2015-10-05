package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.BaseEntity;
import org.realdolmen.webbroker.model.user.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
@RequestScoped
public class UserRepository extends GenericRepo<User> {

    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from users u where u.userName = :username", User.class).setParameter("username", username);
        List<User> resultList = query.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0);
    }

}
