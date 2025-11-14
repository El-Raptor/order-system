package com.raptor.ordersystem.service;

import com.raptor.ordersystem.entity.Order;
import com.raptor.ordersystem.entity.OrderItem;
import com.raptor.ordersystem.repository.OrderItemRepository;
import com.raptor.ordersystem.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService {
    private final OrderRepository orderRepo;

    private final OrderItemRepository orderItemRepo;

    public OrderService(OrderRepository orderRepo, OrderItemRepository orderItemRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
    }

    public List<Order> findByUserId(Integer id) {
        return orderRepo.findByUserId(id);
    }

    public Order findById(Integer id) {
        return orderRepo.findById(id).orElse(null);
    }

    @Transactional
    public Order save(Order order) {
        List<OrderItem> items = order.getOrderItems();
        order = Order.builder()
                .user(order.getUser())
                .orderDate(order.getOrderDate())
                .total(order.getTotal())
                .build();

        Order savedOrder = orderRepo.save(order);

        items.forEach(i -> i.setOrder(savedOrder));

        savedOrder.setOrderItems(new ArrayList<>(items));
        orderRepo.save(savedOrder);

        return savedOrder;
    }

    public void deleteById(Integer id) {
        orderRepo.deleteById(id);
    }

}
