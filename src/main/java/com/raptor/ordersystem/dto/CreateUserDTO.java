package com.raptor.ordersystem.dto;

import com.raptor.ordersystem.utility.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDTO {
    private String name;
    private String email;
    private Role role;
    private String password;
}
