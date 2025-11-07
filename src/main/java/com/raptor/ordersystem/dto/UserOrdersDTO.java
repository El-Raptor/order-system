package com.raptor.ordersystem.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrdersDTO {
    private List<OrderDTO> orders;
}
