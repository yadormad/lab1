package com.yador.lab1.dao.impl.jpa;

import com.yador.lab1.dao.DaoFactory;
import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.model.entity.ClientEntity;
import com.yador.lab1.model.entity.MachinistEntity;
import com.yador.lab1.model.entity.OrderEntity;
import com.yador.lab1.model.entity.OrderStatusEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaDaoFactory implements DaoFactory {

    private GenericDao<Long, ClientEntity> clientDao;
    private GenericDao<Long, MachinistEntity> machinistDao;
    private GenericDao<Long, OrderEntity> orderDao;
    private GenericDao<Long, OrderStatusEntity> orderStatusDao;

    public JpaDaoFactory() {
        EntityManager manager = Persistence
                .createEntityManagerFactory("PitStopResource")
                .createEntityManager();
        clientDao = new ClientJpaDao(manager);
        machinistDao = new MachinistJpaDao(manager);
        orderDao = new OrderJpaDao(manager);
        orderStatusDao = new OrderStatusJpaDao(manager);
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
