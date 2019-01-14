package com.yador.lab1.dao.impl.jpa;

import com.yador.lab1.model.entity.OrderEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderJpaDao extends JpaDao<Long, OrderEntity> {

    public OrderJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public OrderEntity get(Long id) {
        return entityManager.find(OrderEntity.class, id);
    }

    @Override
    public List<OrderEntity> getAll() {
        return entityManager.createQuery("SELECT c FROM OrderEntity c", OrderEntity.class).getResultList();
    }
}
