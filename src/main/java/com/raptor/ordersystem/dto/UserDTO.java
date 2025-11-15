package com.raptor.ordersystem.dto;

import com.raptor.ordersystem.utility.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private Role role;
    private String password;
    private List<OrderDTO> orders;
}
