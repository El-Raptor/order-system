package com.raptor.ordersystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@IdClass(OrderItemId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Positive
    private int quantity;

    @Positive
    private Double price;

    @Transient
    private Double total;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
