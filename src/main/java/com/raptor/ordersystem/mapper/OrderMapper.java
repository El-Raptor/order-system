package com.raptor.ordersystem.mapper;

import com.raptor.ordersystem.dto.CreateOrderDTO;
import com.raptor.ordersystem.dto.OrderDTO;
import com.raptor.ordersystem.dto.OrderSummaryDTO;
import com.raptor.ordersystem.entity.Order;

public class OrderMapper {

    public static OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .user(UserMapper.toDto(order.getUser()))
                .orderDate(order.getOrderDate())
                .items(order.getOrderItems().stream()
                        .map(OrderItemMapper::toDto)
                        .toList())
                .build();
    }

    public static Order toEntity(OrderDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .user(UserMapper.toEntity(orderDTO.getUser()))
                .orderDate(orderDTO.getOrderDate())
                .orderItems(orderDTO.getItems().stream()
                        .map(OrderItemMapper::toEntity)
                        .toList())
                .total(orderDTO.getTotal())
                .build();
    }

    public static Order toEntity(OrderSummaryDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .total(orderDTO.getTotal())
                .build();
    }

    public static Order toEntity(CreateOrderDTO orderDTO) {
        return Order.builder()
                .user(UserMapper.toEntity(orderDTO.getUser()))
                .orderDate(orderDTO.getOrderDate())
                .orderItems(orderDTO.getItems().stream()
                        .map(OrderItemMapper::toEntity)
                        .toList())
                .total(orderDTO.getTotal())
                .build();
    }
}
