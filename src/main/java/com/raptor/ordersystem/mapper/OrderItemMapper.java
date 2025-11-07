package com.raptor.ordersystem.mapper;

import com.raptor.ordersystem.dto.CreateOrderItemDTO;
import com.raptor.ordersystem.dto.OrderItemDTO;
import com.raptor.ordersystem.entity.Order;
import com.raptor.ordersystem.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItemDTO toDto(OrderItem item) {
        return OrderItemDTO.builder()
                .orderId(item.getOrder().getOrderId())
                .sequence(item.getSequence())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .product(ProductMapper.toDto(item.getProduct()))
                .build();
    }

    public static OrderItem toEntity(OrderItemDTO itemDTO) {
        return OrderItem.builder()
                .order(Order.builder()
                        .orderId(itemDTO.getOrderId())
                        .build()
                )
                .sequence(itemDTO.getSequence())
                .quantity(itemDTO.getQuantity())
                .price(itemDTO.getPrice())
                .product(ProductMapper.toEntity(itemDTO.getProduct()))
                .build();
    }

    public static OrderItem toEntity(CreateOrderItemDTO itemDTO) {
        return OrderItem.builder()
                .order(Order.builder()
                        .orderId(itemDTO.getOrderId())
                        .build()
                )
                .quantity(itemDTO.getQuantity())
                .price(itemDTO.getPrice())
                .product(ProductMapper.toEntity(itemDTO.getProduct()))
                .build();
    }
}
