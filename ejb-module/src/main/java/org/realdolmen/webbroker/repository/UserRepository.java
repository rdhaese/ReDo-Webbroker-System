package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.repo.GenericRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;

@Named
@RequestScoped
public class UserRepository extends GenericRepo<User> {

    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username", User.class).setParameter(username, username);
        return query.getResultList().get(0);
    }


}
