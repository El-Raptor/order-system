package com.raptor.ordersystem.mapper;

import com.raptor.ordersystem.dto.AddOrderItemDTO;
import com.raptor.ordersystem.dto.CreateOrderItemDTO;
import com.raptor.ordersystem.dto.OrderItemDTO;
import com.raptor.ordersystem.entity.Order;
import com.raptor.ordersystem.entity.OrderItem;
import com.raptor.ordersystem.entity.Product;

public class OrderItemMapper {

    public static OrderItemDTO toDto(OrderItem item) {
        return OrderItemDTO.builder()
                .orderItemId(item.getOrderItemId())
                .productId(item.getProduct().getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .total(item.getTotal())
                .build();
    }

    public static OrderItem toEntity(OrderItemDTO dto) {
        return OrderItem.builder()
                .order(Order.builder()
                        .orderId(dto.getOrderId())
                        .build()
                )
                .orderItemId(dto.getOrderItemId())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .product(Product.builder().productId(dto.getProductId()).build())
                .build();
    }

    public static OrderItem toEntity(CreateOrderItemDTO itemDTO) {
        return OrderItem.builder()
                .product(Product.builder().productId(itemDTO.getProductId()).build())
                .quantity(itemDTO.getQuantity())
                .price(itemDTO.getPrice())
                .build();
    }

    public static OrderItem toEntity(AddOrderItemDTO dto) {
        return OrderItem.builder()
                .order(Order.builder().orderId(dto.getOrderId()).build())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .product(Product.builder().productId(dto.getProductId()).build())
                .build();
    }

}
