package com.yador.lab1.dao.impl.jpa;

import com.yador.lab1.dao.DaoFactory;
import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.model.entity.ClientEntity;
import com.yador.lab1.model.entity.MachinistEntity;
import com.yador.lab1.model.entity.OrderEntity;
import com.yador.lab1.model.entity.OrderStatusEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public class JpaDaoFactory implements DaoFactory {

    private GenericDao<Long, ClientEntity> clientDao;
    private GenericDao<Long, MachinistEntity> machinistDao;
    private GenericDao<Long, OrderEntity> orderDao;
    private GenericDao<Long, OrderStatusEntity> orderStatusDao;

    public JpaDaoFactory(EntityManager entityManager) {
        clientDao = new ClientJpaDao(entityManager);
        machinistDao = new MachinistJpaDao(entityManager);
        orderDao = new OrderJpaDao(entityManager);
        orderStatusDao = new OrderStatusJpaDao(entityManager);
    }

    @Override
    public GenericDao<Long, ClientEntity> getClientDao() {
        return clientDao;
    }

    @Override
    public GenericDao<Long, MachinistEntity> getMachinistDao() {
        return machinistDao;
    }

    @Override
    public GenericDao<Long, OrderEntity> getOrderDao() {
        return orderDao;
    }

    @Override
    public GenericDao<Long, OrderStatusEntity> getOrderStatusDao() {
        return orderStatusDao;
    }
}
