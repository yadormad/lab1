package com.yador.lab1.dao.converter.impl;

import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.model.dto.Order;
import com.yador.lab1.model.entity.ClientEntity;
import com.yador.lab1.model.entity.MachinistEntity;
import com.yador.lab1.model.entity.OrderEntity;
import com.yador.lab1.model.entity.OrderStatusEntity;

import java.sql.Date;

public class OrderJpaDtoConverterImpl extends JpaDtoConverter<OrderEntity, Order> {

    private final GenericDao<Long, OrderEntity> orderDao;
    private final GenericDao<Long, ClientEntity> clientDao;
    private final GenericDao<Long, MachinistEntity> machinistDao;
    private final GenericDao<Long, OrderStatusEntity> orderStatusDao;

    private OrderJpaDtoConverterImpl(GenericDao<Long, OrderEntity> orderDao,
                                     GenericDao<Long, ClientEntity> clientDao,
                                     GenericDao<Long, MachinistEntity> machinistDao,
                                     GenericDao<Long, OrderStatusEntity> orderStatusDao) {
        this.orderDao = orderDao;
        this.clientDao = clientDao;
        this.machinistDao = machinistDao;
        this.orderStatusDao = orderStatusDao;
    }

    public static class Builder {

        private GenericDao<Long, OrderEntity> orderDao;
        private GenericDao<Long, ClientEntity> clientDao;
        private GenericDao<Long, MachinistEntity> machinistDao;
        private GenericDao<Long, OrderStatusEntity> orderStatusDao;

        public Builder() {}

        public Builder setOrderDao(GenericDao<Long, OrderEntity> orderDao) {
            this.orderDao = orderDao;
            return this;
        }

        public Builder setClientDao(GenericDao<Long, ClientEntity> clientDao) {
            this.clientDao = clientDao;
            return this;
        }

        public Builder setMachinistDao(GenericDao<Long, MachinistEntity> machinistDao) {
            this.machinistDao = machinistDao;
            return this;
        }

        public Builder setOrderStatusDao(GenericDao<Long, OrderStatusEntity> orderStatusDao) {
            this.orderStatusDao = orderStatusDao;
            return this;
        }

        public OrderJpaDtoConverterImpl build() {
            return new OrderJpaDtoConverterImpl(
                    orderDao,
                    clientDao,
                    machinistDao,
                    orderStatusDao
            );
        }
    }

    @Override
    public OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = (order.getId() != null) ? orderDao.get(order.getId()) : new OrderEntity();
        orderEntity.setDescription(order.getDescription());
        orderEntity.setStartDate(new Date(order.getStartDate().getTime()));
        orderEntity.setEndDate(new Date(order.getEndDate().getTime()));
        orderEntity.setFullCost(order.getCost());
        return importRelations(orderEntity, order);
    }

    @Override
    public Order toDto(OrderEntity orderEntity) {
        return new Order()
                .setId(orderEntity.getId())
                .setDescription(orderEntity.getDescription())
                .setCost(orderEntity.getFullCost())
                .setStartDate(orderEntity.getStartDate())
                .setEndDate(orderEntity.getEndDate())
                .setClient(orderEntity.getClientEntity().getId())
                .setMachinist(orderEntity.getMachinistEntity().getId())
                .setStatus(orderEntity.getOrderStatusEntity().getId());
    }

    private OrderEntity importRelations(OrderEntity orderEntity, Order order) {
        orderEntity.setClientEntity(clientDao.get(order.getClient()));
        orderEntity.setMachinistEntity(machinistDao.get(order.getMachinist()));
        orderEntity.setOrderStatusEntity(orderStatusDao.get(order.getStatus()));
        return orderEntity;
    }
}
