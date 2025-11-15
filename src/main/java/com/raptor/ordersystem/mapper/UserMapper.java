package com.raptor.ordersystem.mapper;

import com.raptor.ordersystem.dto.*;
import com.raptor.ordersystem.entity.User;

import java.util.Collections;

public class UserMapper {

    public static UserSummaryDTO toSummaryDTO(User user) {
        return UserSummaryDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .orders(user.getOrders() != null ?
                        user.getOrders().stream()
                                .map(OrderMapper::toDto)
                                .toList() :
                        Collections.emptyList())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .orders(userDTO.getOrders() != null ?
                        userDTO.getOrders().stream()
                                .map(OrderMapper::toEntity)
                                .toList() :
                        Collections.emptyList())
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
