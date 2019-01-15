package com.yador.lab1.controller;

import com.yador.lab1.dao.DaoFactory;
import com.yador.lab1.dao.DeleteException;
import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.dao.converter.EntityDtoConverter;
import com.yador.lab1.dao.converter.impl.OrderJpaDtoConverterImpl;
import com.yador.lab1.dao.converter.impl.OrderStatusJpaDtoConverterImpl;
import com.yador.lab1.dao.impl.jpa.JpaDaoFactory;
import com.yador.lab1.model.dto.Order;
import com.yador.lab1.model.dto.OrderStatus;
import com.yador.lab1.model.entity.ClientEntity;
import com.yador.lab1.model.entity.MachinistEntity;
import com.yador.lab1.model.entity.OrderEntity;
import com.yador.lab1.model.entity.OrderStatusEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class OrderController {

    private GenericDao<Long, OrderEntity> orderDao;
    private GenericDao<Long, OrderStatusEntity> orderStatusDao;
    private EntityDtoConverter<OrderEntity, Order> orderEntityDtoConverter;
    private EntityDtoConverter<OrderStatusEntity, OrderStatus> orderStatusEntityDtoConverter;

    @PostConstruct
    public void init() {
        DaoFactory daoFactory = new JpaDaoFactory();

        GenericDao<Long, ClientEntity> clientDao = daoFactory.getClientDao();
        GenericDao<Long, MachinistEntity> machinistDao = daoFactory.getMachinistDao();
        orderStatusDao = daoFactory.getOrderStatusDao();

        orderStatusEntityDtoConverter = new OrderStatusJpaDtoConverterImpl(orderStatusDao);

        orderDao = daoFactory.getOrderDao();
        orderEntityDtoConverter = new OrderJpaDtoConverterImpl.Builder()
                .setOrderDao(orderDao)
                .setOrderStatusDao(orderStatusDao)
                .setClientDao(clientDao)
                .setMachinistDao(machinistDao)
                .build();
    }

    public List<Order> getAll() {
        return orderEntityDtoConverter.allToDto(orderDao.getAll());
    }

    public void editOrder(Order client) {
        orderDao.update(orderEntityDtoConverter.toEntity(client));
    }

    public void addOrder(Order client) {
        orderDao.add(orderEntityDtoConverter.toEntity(client));
    }

    public void deleteOrder(Long id) throws DeleteException {
        orderDao.delete(id);
    }

    public Order createOrder(Long id,
            String description,
            Long clientId,
            Long machinistId,
            Date startDate,
            Date endDate,
            Double cost,
            Long statusId) {
        return new Order()
                .setClient(clientId)
                .setCost(cost)
                .setDescription(description)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setId(id)
                .setMachinist(machinistId)
                .setStatus(statusId);
    }

    public List<OrderStatus> getAllOrderStatuses() {
        return orderStatusEntityDtoConverter.allToDto(orderStatusDao.getAll());
    }

    public OrderStatus getOrderStatus(Order order) {
        return orderStatusEntityDtoConverter.toDto(orderStatusDao.get(order.getId()));
    }

}
