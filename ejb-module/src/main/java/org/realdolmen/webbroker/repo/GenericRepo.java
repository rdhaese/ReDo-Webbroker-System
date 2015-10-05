package org.realdolmen.webbroker.repo;

import org.realdolmen.webbroker.model.BaseEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * A JPA Repository that can be used for every class extending BaseEntity.
 * Created by RDEAX37 on 4/10/2015.
 */
@Stateless
@LocalBean
public class GenericRepo<T extends BaseEntity> {

    @PersistenceContext
    protected
    EntityManager entityManager;

    /**
     * @param entity to add to the persistence context
     */
    public void add(T entity){
        entityManager.persist(entity);
    }

    /**
     * @param type of entity to get from persistence context
     * @param id of entity to get from persistence context
     * @return the found entity, or null
     */
    public T find(Class<T> type, long id){
       return entityManager.find(type,id);
    }

    /**
     * Makes sure the entity in the persistence context is up to date with the given entity
     * @param entity to update
     * @return the updated entity from the persistence context
     */
    public T update(T entity){
       return entityManager.merge(entity);
    }

    /**
     * @param entity to remove from the persistence context
     */
    public void remove(T entity){
        entityManager.remove(entityManager.merge(entity));
    }

    /**
     * @param type of entity to remove from the persistence context
     * @param id of entity to remove from the persistence context
     */
    public void remove(Class type, long id){
        entityManager.remove(find(type,id));
    }
}
