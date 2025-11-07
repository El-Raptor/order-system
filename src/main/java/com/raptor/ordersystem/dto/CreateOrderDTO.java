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
    private UserDTO user;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> items;
    private double total;
}
