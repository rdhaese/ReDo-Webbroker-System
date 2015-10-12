package org.realdolmen.webbroker.repository;

import org.realdolmen.webbroker.model.Discount;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by RDEAX37 on 10/10/2015.
 */
@Stateless
@LocalBean
public class DiscountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Discount discount) {
        entityManager.persist(discount);
    }

    public Discount getOnName(String name) {
        return entityManager.createQuery("SELECT d From Discount d where d.name = :name", Discount.class).setParameter("name", name).getSingleResult();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
