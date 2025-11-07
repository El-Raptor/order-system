package com.raptor.ordersystem.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class OrderSummaryDTO {
    private int orderId;
    private double total;
}
