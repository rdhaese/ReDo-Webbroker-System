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
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class UserRepository implements Serializable{

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from users u where u.userName = :username", User.class).setParameter("username", username);
        List<User> resultList = query.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0);
    }

    public void add(User entity) {
        entityManager.persist(entity);
    }


    public User find(long id) {
        return entityManager.find(User.class, id);
    }


    public User update(User user) {
        return entityManager.merge(user);
    }


    public void remove(User user) {
        entityManager.remove(entityManager.merge(user));
        entityManager.flush();
    }

    public List<User> getAllUsers(){
        return entityManager.createQuery("SELECT u FROM users u", User.class).getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
