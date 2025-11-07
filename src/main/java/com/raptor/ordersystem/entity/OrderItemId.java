package com.raptor.ordersystem.entity;


import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class OrderItemId implements Serializable {
    private final int orderId;
    private final int sequence;

    public OrderItemId(int orderId, int sequence) {
        this.orderId = orderId;
        this.sequence = sequence;
    }
}
