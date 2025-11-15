package com.raptor.ordersystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_items_id")
    @EqualsAndHashCode.Include
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
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
