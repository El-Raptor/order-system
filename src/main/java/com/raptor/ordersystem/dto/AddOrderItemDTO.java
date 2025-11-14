package com.raptor.ordersystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddOrderItemDTO {
    private int orderId;
    private int quantity;
    private double price;
    private int productId;
}
