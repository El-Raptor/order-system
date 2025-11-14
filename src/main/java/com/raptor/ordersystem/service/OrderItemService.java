package com.raptor.ordersystem.service;

import com.raptor.ordersystem.dto.AddOrderItemDTO;
import com.raptor.ordersystem.dto.CreateOrderItemDTO;
import com.raptor.ordersystem.entity.OrderItem;
import com.raptor.ordersystem.mapper.OrderItemMapper;
import com.raptor.ordersystem.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepo;

    OrderItemService(OrderItemRepository orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    public List<OrderItem> findAllByOrderId(int orderId) {
        return orderItemRepo.findAllByOrderId(orderId);
    }

    public OrderItem findById(int orderItemId) {
        return orderItemRepo.findById(orderItemId).orElse(null);
    }

    public OrderItem createOrderItem(AddOrderItemDTO dto) {
        return orderItemRepo.save(OrderItemMapper.toEntity(dto));
    }

    public void deleteAllByOrderId(Integer orderId) {
        orderItemRepo.deleteAllByOrderId(orderId);
    }

    public void deleteById(int orderItemId) {
        orderItemRepo.deleteById(orderItemId);
    }
}
