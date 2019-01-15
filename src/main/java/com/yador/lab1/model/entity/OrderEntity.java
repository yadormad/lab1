package com.yador.lab1.model.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ORDER_TABLE", schema = "PUBLIC")
public class OrderEntity {
    private long id;
    private String description;
    private ClientEntity clientEntity;
    private MachinistEntity machinistEntity;
    private Date startDate;
    private Date endDate;
    private OrderStatusEntity orderStatusEntity;
    private double fullCost;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    @ManyToOne
    @JoinColumn(name = "machinist_id", nullable = false)
    public MachinistEntity getMachinistEntity() {
        return machinistEntity;
    }

    public void setMachinistEntity(MachinistEntity machinistEntity) {
        this.machinistEntity = machinistEntity;
    }

    @ManyToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    public OrderStatusEntity getOrderStatusEntity() {
        return orderStatusEntity;
    }

    public void setOrderStatusEntity(OrderStatusEntity orderStatusEntity) {
        this.orderStatusEntity = orderStatusEntity;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "START_DATE")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "FULL_COST")
    public double getFullCost() {
        return fullCost;
    }

    public void setFullCost(double fullCost) {
        this.fullCost = fullCost;
    }
}
