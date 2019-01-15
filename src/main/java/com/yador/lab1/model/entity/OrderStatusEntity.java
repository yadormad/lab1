package com.yador.lab1.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ORDER_STATUS_TABLE", schema = "PUBLIC")
public class OrderStatusEntity {
    private long id;
    private String status;
    private Set<OrderEntity> orderEntitySet;

    @OneToMany(mappedBy = "orderStatusEntity")
    public Set<OrderEntity> getOrderEntitySet() {
        return orderEntitySet;
    }

    public void setOrderEntitySet(Set<OrderEntity> orderEntitySet) {
        this.orderEntitySet = orderEntitySet;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
