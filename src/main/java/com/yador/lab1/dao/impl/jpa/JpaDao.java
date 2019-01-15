package com.yador.lab1.dao.impl.jpa;

import com.yador.lab1.dao.DeleteException;
import com.yador.lab1.dao.GenericDao;

import javax.persistence.EntityManager;

public abstract class JpaDao<K, O> implements GenericDao<K, O> {

    final EntityManager entityManager;

    JpaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public O add(O entity) {
        entityManager.getTransaction().begin();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(K id) throws DeleteException {
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(O entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
}
