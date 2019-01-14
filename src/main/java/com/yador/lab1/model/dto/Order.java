package com.yador.lab1.model.dto;

import java.util.Date;

public class Order {
    private Long id;
    private String description;
    private Client client;
    private Machinist machinist;
    private Date startDate;
    private Date endDate;
    private Double cost;
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Client getClient() {
        return client;
    }

    public Machinist getMachinist() {
        return machinist;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Double getCost() {
        return cost;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Order setDescription(String description) {
        this.description = description;
        return this;
    }

    public Order setClient(Client client) {
        this.client = client;
        return this;
    }

    public Order setMachinist(Machinist machinist) {
        this.machinist = machinist;
        return this;
    }

    public Order setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Order setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Order setCost(Double cost) {
        this.cost = cost;
        return this;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }
}
