package com.raptor.ordersystem.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserSummaryDTO {
    private int id;
    private String name;
    private String email;
}
