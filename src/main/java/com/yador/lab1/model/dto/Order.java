package com.yador.lab1.model.dto;

import java.util.Date;

public class Order {
    private Long id;
    private String description;
    private Long clientId;
    private Long machinistId;
    private Date startDate;
    private Date endDate;
    private Double cost;
    private Long statusId;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Long getClient() {
        return clientId;
    }

    public Long getMachinist() {
        return machinistId;
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

    public Long getStatus() {
        return statusId;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Order setDescription(String description) {
        this.description = description;
        return this;
    }

    public Order setClient(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Order setMachinist(Long machinistId) {
        this.machinistId = machinistId;
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

    public Order setStatus(Long statusId) {
        this.statusId = statusId;
        return this;
    }
}
