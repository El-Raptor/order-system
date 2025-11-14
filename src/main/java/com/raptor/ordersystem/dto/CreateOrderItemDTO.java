package com.raptor.ordersystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderItemDTO {
    private int productId;
    private int quantity;
    private double price;
}
