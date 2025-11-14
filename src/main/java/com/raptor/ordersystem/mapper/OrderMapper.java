package com.raptor.ordersystem.mapper;

import com.raptor.ordersystem.dto.*;
import com.raptor.ordersystem.entity.Order;
import com.raptor.ordersystem.entity.User;

import java.util.Collections;

public class OrderMapper {

    public static OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .userId(order.getUser().getId())
                .orderDate(order.getOrderDate())
                .items(order.getOrderItems() != null
                        ? order.getOrderItems().stream()
                        .map(OrderItemMapper::toDto)
                        .toList()
                        : Collections.emptyList()
                )
                .build();
    }

    public static Order toEntity(OrderDTO dto) {
        return Order.builder()
                .orderId(dto.getOrderId())
                .user(User.builder().id(dto.getUserId()).build())
                .orderDate(dto.getOrderDate())
                .orderItems(dto.getItems().stream()
                        .map(OrderItemMapper::toEntity)
                        .toList())
                .total(dto.getTotal())
                .build();
    }

    public static Order toEntity(OrderSummaryDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .total(orderDTO.getTotal())
                .build();
    }

    public static Order toEntity(CreateOrderDTO dto) {
        return Order.builder()
                .user(User.builder()
                        .id(dto.getUserId())
                        .build())
                .orderDate(dto.getOrderDate())
                .orderItems(
                        dto.getItems() != null
                                ? dto.getItems().stream()
                                .map(OrderItemMapper::toEntity)
                                .toList()
                                : Collections.emptyList()
                )
                .total(dto.getTotal())
                .build();
    }
}
