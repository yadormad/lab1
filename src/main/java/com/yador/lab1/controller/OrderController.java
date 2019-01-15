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
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Stateless
public class OrderController {

    private GenericDao<Long, OrderEntity> orderDao;
    private GenericDao<Long, OrderStatusEntity> orderStatusDao;
    private EntityDtoConverter<OrderEntity, Order> orderEntityDtoConverter;
    private EntityDtoConverter<OrderStatusEntity, OrderStatus> orderStatusEntityDtoConverter;

    @PostConstruct
    public void init() {
        DaoFactory daoFactory = new JpaDaoFactory(new EntityManager() {
            @Override
            public void persist(Object o) {

            }

            @Override
            public <T> T merge(T t) {
                return null;
            }

            @Override
            public void remove(Object o) {

            }

            @Override
            public <T> T find(Class<T> aClass, Object o) {
                return null;
            }

            @Override
            public <T> T find(Class<T> aClass, Object o, Map<String, Object> map) {
                return null;
            }

            @Override
            public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType) {
                return null;
            }

            @Override
            public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType, Map<String, Object> map) {
                return null;
            }

            @Override
            public <T> T getReference(Class<T> aClass, Object o) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public void setFlushMode(FlushModeType flushModeType) {

            }

            @Override
            public FlushModeType getFlushMode() {
                return null;
            }

            @Override
            public void lock(Object o, LockModeType lockModeType) {

            }

            @Override
            public void lock(Object o, LockModeType lockModeType, Map<String, Object> map) {

            }

            @Override
            public void refresh(Object o) {

            }

            @Override
            public void refresh(Object o, Map<String, Object> map) {

            }

            @Override
            public void refresh(Object o, LockModeType lockModeType) {

            }

            @Override
            public void refresh(Object o, LockModeType lockModeType, Map<String, Object> map) {

            }

            @Override
            public void clear() {

            }

            @Override
            public void detach(Object o) {

            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public LockModeType getLockMode(Object o) {
                return null;
            }

            @Override
            public void setProperty(String s, Object o) {

            }

            @Override
            public Map<String, Object> getProperties() {
                return null;
            }

            @Override
            public Query createQuery(String s) {
                return null;
            }

            @Override
            public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
                return null;
            }

            @Override
            public Query createQuery(CriteriaUpdate criteriaUpdate) {
                return null;
            }

            @Override
            public Query createQuery(CriteriaDelete criteriaDelete) {
                return null;
            }

            @Override
            public <T> TypedQuery<T> createQuery(String s, Class<T> aClass) {
                return null;
            }

            @Override
            public Query createNamedQuery(String s) {
                return null;
            }

            @Override
            public <T> TypedQuery<T> createNamedQuery(String s, Class<T> aClass) {
                return null;
            }

            @Override
            public Query createNativeQuery(String s) {
                return null;
            }

            @Override
            public Query createNativeQuery(String s, Class aClass) {
                return null;
            }

            @Override
            public Query createNativeQuery(String s, String s1) {
                return null;
            }

            @Override
            public StoredProcedureQuery createNamedStoredProcedureQuery(String s) {
                return null;
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String s) {
                return null;
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String s, Class... classes) {
                return null;
            }

            @Override
            public StoredProcedureQuery createStoredProcedureQuery(String s, String... strings) {
                return null;
            }

            @Override
            public void joinTransaction() {

            }

            @Override
            public boolean isJoinedToTransaction() {
                return false;
            }

            @Override
            public <T> T unwrap(Class<T> aClass) {
                return null;
            }

            @Override
            public Object getDelegate() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public boolean isOpen() {
                return false;
            }

            @Override
            public EntityTransaction getTransaction() {
                return null;
            }

            @Override
            public EntityManagerFactory getEntityManagerFactory() {
                return null;
            }

            @Override
            public CriteriaBuilder getCriteriaBuilder() {
                return null;
            }

            @Override
            public Metamodel getMetamodel() {
                return null;
            }

            @Override
            public <T> EntityGraph<T> createEntityGraph(Class<T> aClass) {
                return null;
            }

            @Override
            public EntityGraph<?> createEntityGraph(String s) {
                return null;
            }

            @Override
            public EntityGraph<?> getEntityGraph(String s) {
                return null;
            }

            @Override
            public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> aClass) {
                return null;
            }
        });

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
