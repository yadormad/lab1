package com.yador.lab1.model.dto;

import java.util.Set;

public class Machinist {
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Double valueCost;
    private Set<Order> machinistOrders;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Double getValueCost() {
        return valueCost;
    }

    public Set<Order> getMachinistOrders() {
        return machinistOrders;
    }

    public Machinist setId(Long id) {
        this.id = id;
        return this;
    }

    public Machinist setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Machinist setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Machinist setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public Machinist setValueCost(Double valueCost) {
        this.valueCost = valueCost;
        return this;
    }

    public Machinist setMachinistOrders(Set<Order> machinistOrders) {
        this.machinistOrders = machinistOrders;
        return this;
    }
}
