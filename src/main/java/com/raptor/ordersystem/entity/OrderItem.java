package com.raptor.ordersystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "order_items")
@IdClass(OrderItemId.class)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int sequence;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    @EqualsAndHashCode.Include
    private Order order;

    @Positive
    private int quantity;

    @Positive
    private double price;

    @Transient
    private double total;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
