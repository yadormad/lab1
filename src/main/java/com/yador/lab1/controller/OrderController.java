package com.yador.lab1.controller;

import com.yador.lab1.dao.DaoFactory;
import com.yador.lab1.dao.DeleteException;
import com.yador.lab1.dao.GenericDao;
import com.yador.lab1.dao.converter.EntityDtoConverter;
import com.yador.lab1.dao.converter.impl.ClientJpaDtoConverterImpl;
import com.yador.lab1.dao.converter.impl.MachinistJpaDtoConverterImpl;
import com.yador.lab1.dao.converter.impl.OrderJpaDtoConverterImpl;
import com.yador.lab1.dao.converter.impl.OrderStatusJpaDtoConverterImpl;
import com.yador.lab1.dao.impl.jpa.JpaDaoFactory;
import com.yador.lab1.model.dto.Client;
import com.yador.lab1.model.dto.Machinist;
import com.yador.lab1.model.dto.Order;
import com.yador.lab1.model.dto.OrderStatus;
import com.yador.lab1.model.entity.ClientEntity;
import com.yador.lab1.model.entity.MachinistEntity;
import com.yador.lab1.model.entity.OrderEntity;
import com.yador.lab1.model.entity.OrderStatusEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrderController {

    private GenericDao<Long, OrderEntity> orderDao;
    private EntityDtoConverter<OrderEntity, Order> orderEntityDtoConverter;

    @PostConstruct
    public void init() {
        DaoFactory daoFactory = new JpaDaoFactory();

        GenericDao<Long, ClientEntity> clientDao = daoFactory.getClientDao();
        GenericDao<Long, MachinistEntity> machinistDao = daoFactory.getMachinistDao();
        GenericDao<Long, OrderStatusEntity> orderStatusDao = daoFactory.getOrderStatusDao();

        EntityDtoConverter<ClientEntity, Client> clientEntityDtoConverter = new ClientJpaDtoConverterImpl(clientDao);
        EntityDtoConverter<MachinistEntity, Machinist> machinistEntityDtoConverter = new MachinistJpaDtoConverterImpl(machinistDao);
        EntityDtoConverter<OrderStatusEntity, OrderStatus> orderStatusEntityDtoConverter = new OrderStatusJpaDtoConverterImpl(orderStatusDao);

        orderDao = daoFactory.getOrderDao();
        orderEntityDtoConverter = new OrderJpaDtoConverterImpl.Builder()
                .setOrderStatusEntityDtoConverter(orderStatusEntityDtoConverter)
                .setClientEntityDtoConverter(clientEntityDtoConverter)
                .setMachinistEntityDtoConverter(machinistEntityDtoConverter)
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

    public Order buildOrder() {
        return new Order();
    }
}
