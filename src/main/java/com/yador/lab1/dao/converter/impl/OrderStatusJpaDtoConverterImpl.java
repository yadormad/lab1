package com.yador.lab1.dao.converter.impl;

import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.model.dto.OrderStatus;
import com.yador.lab1.model.entity.OrderStatusEntity;

public class OrderStatusJpaDtoConverterImpl extends JpaDtoConverter<OrderStatusEntity, OrderStatus> {

    private GenericDao<Long, OrderStatusEntity> orderStatusDao;

    public OrderStatusJpaDtoConverterImpl(GenericDao<Long, OrderStatusEntity> orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    public OrderStatusEntity toEntity(OrderStatus orderStatus) {
        return orderStatusDao.get(orderStatus.getId());
    }

    @Override
    public OrderStatus toDto(OrderStatusEntity orderStatusEntity) {
        return new OrderStatus(orderStatusEntity.getId(), orderStatusEntity.getStatus());
    }
}
