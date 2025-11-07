package com.raptor.ordersystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private int orderId;
    private int sequence;
    private int quantity;
    private double price;
    private ProductDTO product;
}

