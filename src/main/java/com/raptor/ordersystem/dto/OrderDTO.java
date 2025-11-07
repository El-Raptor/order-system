package com.raptor.ordersystem.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private int orderId;
    private UserDTO user;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> items;
}
