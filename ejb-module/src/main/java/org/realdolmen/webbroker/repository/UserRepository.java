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

/**
 * Repository for the {@link User} entity (and child classes)
 * @author Robin D'Haese
 */
@Stateless
@LocalBean
public class UserRepository implements Serializable{

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Method to find a user on his username
     * @param username to search user for
     * @return the found user, or null
     */
    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from users u where u.userName = :username", User.class).setParameter("username", username);
        List<User> resultList = query.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0);
    }

    /**
     * Adds a user to the persistence context.
     * @param entity to add
     */
    public void add(User entity) {
        entityManager.persist(entity);
    }


    /**
     * Find a user on his id
     * @param id to search user for
     * @return the found user, or null
     */
    public User find(long id) {
        return entityManager.find(User.class, id);
    }


    /**
     * Update a user that already exists in the persistence context.
     * @param user to update
     * @return the updated user.
     */
    public User update(User user) {
        return entityManager.merge(user);
    }


    /**
     * Removes the user from the persistence context.
     * @param user to remove
     */
    public void remove(User user) {
        entityManager.remove(entityManager.merge(user));
        entityManager.flush();
    }

    /**
     * @return all users in the persistence context, or an empty list if none are found.
     */
    public List<User> getAllUsers(){
        return entityManager.createQuery("SELECT u FROM users u", User.class).getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
