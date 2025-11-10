package com.raptor.ordersystem.mapper;

import com.raptor.ordersystem.dto.CreateUserDTO;
import com.raptor.ordersystem.dto.UserDTO;
import com.raptor.ordersystem.dto.UserOrdersDTO;
import com.raptor.ordersystem.dto.UserSummaryDTO;
import com.raptor.ordersystem.entity.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .orders(user.getOrders().stream()
                        .map(OrderMapper::toDto)
                        .toList())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .orders(userDTO.getOrders().stream()
                        .map(OrderMapper::toEntity)
                        .toList())
                .build();
    }

    public static User toEntity(CreateUserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
    }

    public static User toEntity(UserOrdersDTO userDTO) {
        return User.builder()
                .orders(userDTO.getOrders().stream()
                        .map(OrderMapper::toEntity)
                        .toList())
                .build();
    }

    public static User toEntity(UserSummaryDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
    }
}
