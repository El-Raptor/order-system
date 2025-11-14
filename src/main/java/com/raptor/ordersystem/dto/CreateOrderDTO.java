package com.raptor.ordersystem.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDTO {
    private int userId;
    private LocalDateTime orderDate;
    private List<CreateOrderItemDTO> items;
    private double total;
}
