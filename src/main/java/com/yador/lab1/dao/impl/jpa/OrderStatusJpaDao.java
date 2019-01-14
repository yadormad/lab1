package com.yador.lab1.dao.impl.jpa;

import com.yador.lab1.dao.DeleteException;
import com.yador.lab1.model.entity.OrderStatusEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderStatusJpaDao extends JpaDao<Long, OrderStatusEntity> {

    public OrderStatusJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public OrderStatusEntity add(OrderStatusEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) throws DeleteException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(OrderStatusEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OrderStatusEntity get(Long id) {
        return entityManager.find(OrderStatusEntity.class, id);
    }

    @Override
    public List<OrderStatusEntity> getAll() {
        return entityManager.createQuery("SELECT c FROM OrderStatusEntity c", OrderStatusEntity.class).getResultList();
    }
}
