package com.yador.lab1.model.dto;

public class OrderStatus {
    private final Long id;
    private final String status;

    public OrderStatus(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
