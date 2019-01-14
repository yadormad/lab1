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
        return entityManager.merge(entity);
    }

    @Override
    public void delete(K id) throws DeleteException {
        entityManager.remove(get(id));
    }

    @Override
    public void update(O entity) {
        entityManager.persist(entity);
    }
}
