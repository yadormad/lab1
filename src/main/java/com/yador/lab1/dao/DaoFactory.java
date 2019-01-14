package com.yador.lab1.dao;

import com.yador.lab1.model.entity.ClientEntity;
import com.yador.lab1.model.entity.MachinistEntity;
import com.yador.lab1.model.entity.OrderEntity;
import com.yador.lab1.model.entity.OrderStatusEntity;

public interface DaoFactory {
    GenericDao<Long, ClientEntity> getClientDao();
    GenericDao<Long, MachinistEntity> getMachinistDao();
    GenericDao<Long, OrderEntity> getOrderDao();
    GenericDao<Long, OrderStatusEntity> getOrderStatusDao();
}

