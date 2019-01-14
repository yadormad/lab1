package com.yador.lab1.dao.converter.impl;

import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.dao.converter.EntityDtoConverter;
import com.yador.lab1.model.dto.Client;
import com.yador.lab1.model.dto.Machinist;
import com.yador.lab1.model.dto.Order;
import com.yador.lab1.model.dto.OrderStatus;
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

    private final EntityDtoConverter<ClientEntity, Client> clientEntityDtoConverter;
    private final EntityDtoConverter<MachinistEntity, Machinist> machinistEntityDtoConverter;
    private final EntityDtoConverter<OrderStatusEntity, OrderStatus> orderStatusEntityDtoConverter;

    private OrderJpaDtoConverterImpl(GenericDao<Long, OrderEntity> orderDao,
                                     GenericDao<Long, ClientEntity> clientDao,
                                     GenericDao<Long, MachinistEntity> machinistDao,
                                     GenericDao<Long, OrderStatusEntity> orderStatusDao,
                                     EntityDtoConverter<ClientEntity, Client> clientEntityDtoConverter,
                                     EntityDtoConverter<MachinistEntity, Machinist> machinistEntityDtoConverter,
                                     EntityDtoConverter<OrderStatusEntity, OrderStatus> orderStatusEntityDtoConverter) {
        this.orderDao = orderDao;
        this.clientDao = clientDao;
        this.machinistDao = machinistDao;
        this.orderStatusDao = orderStatusDao;
        this.clientEntityDtoConverter = clientEntityDtoConverter;
        this.machinistEntityDtoConverter = machinistEntityDtoConverter;
        this.orderStatusEntityDtoConverter = orderStatusEntityDtoConverter;
    }

    public static class Builder {

        private GenericDao<Long, OrderEntity> orderDao;
        private GenericDao<Long, ClientEntity> clientDao;
        private GenericDao<Long, MachinistEntity> machinistDao;
        private GenericDao<Long, OrderStatusEntity> orderStatusDao;

        private EntityDtoConverter<ClientEntity, Client> clientEntityDtoConverter;
        private EntityDtoConverter<MachinistEntity, Machinist> machinistEntityDtoConverter;
        private EntityDtoConverter<OrderStatusEntity, OrderStatus> orderStatusEntityDtoConverter;

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

        public Builder setClientEntityDtoConverter(EntityDtoConverter<ClientEntity, Client> clientEntityDtoConverter) {
            this.clientEntityDtoConverter = clientEntityDtoConverter;
            return this;
        }

        public Builder setMachinistEntityDtoConverter(EntityDtoConverter<MachinistEntity, Machinist> machinistEntityDtoConverter) {
            this.machinistEntityDtoConverter = machinistEntityDtoConverter;
            return this;
        }

        public Builder setOrderStatusEntityDtoConverter(EntityDtoConverter<OrderStatusEntity, OrderStatus> orderStatusEntityDtoConverter) {
            this.orderStatusEntityDtoConverter = orderStatusEntityDtoConverter;
            return this;
        }

        public OrderJpaDtoConverterImpl build() {
            return new OrderJpaDtoConverterImpl(
                    orderDao,
                    clientDao,
                    machinistDao,
                    orderStatusDao,
                    clientEntityDtoConverter,
                    machinistEntityDtoConverter,
                    orderStatusEntityDtoConverter);
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
                .setClient(clientEntityDtoConverter.toDto(orderEntity.getClientEntity()))
                .setMachinist(machinistEntityDtoConverter.toDto(orderEntity.getMachinistEntity()))
                .setStatus(orderStatusEntityDtoConverter.toDto(orderEntity.getOrderStatusEntity()));
    }

    private OrderEntity importRelations(OrderEntity orderEntity, Order order) {
        orderEntity.setClientEntity(clientDao.get(order.getClient().getId()));
        orderEntity.setMachinistEntity(machinistDao.get(order.getMachinist().getId()));
        orderEntity.setOrderStatusEntity(orderStatusDao.get(order.getStatus().getId()));
        return orderEntity;
    }
}
