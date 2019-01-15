package com.yador.lab1.model.dto;

public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private boolean hasOrders;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isHasOrders() {
        return hasOrders;
    }

    public Client setId(Long id) {
        this.id = id;
        return this;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Client setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public Client setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Client setClientHasOrders(boolean hasOrders) {
        this.hasOrders = hasOrders;
        return this;
    }
}
