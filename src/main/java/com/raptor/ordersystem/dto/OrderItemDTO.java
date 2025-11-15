package com.raptor.ordersystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Long orderItemId;
    private int orderId;
    private int productId;
    private int quantity;
    private double price;
    private double total;
}

