package com.raptor.ordersystem.service;

import com.raptor.ordersystem.dto.CreateOrderDTO;
import com.raptor.ordersystem.entity.Order;
import com.raptor.ordersystem.entity.OrderItem;
import com.raptor.ordersystem.entity.OrderItemId;
import com.raptor.ordersystem.mapper.OrderItemMapper;
import com.raptor.ordersystem.mapper.OrderMapper;
import com.raptor.ordersystem.repository.OrderItemRepository;
import com.raptor.ordersystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
        // 1. Cria uma cópia do pedido, mas sem os itens (para gerar ID)
        List<OrderItem> items = order.getOrderItems();
        order = Order.builder()
                .user(order.getUser())
                .orderDate(order.getOrderDate())
                .total(order.getTotal())
                .build();

        // 2. Salva a Order para gerar o ID
        Order savedOrder = orderRepo.save(order);

        // 3. Associa os itens e o Order agora com ID
        if (items != null && !items.isEmpty()) {
            AtomicInteger sequence = new AtomicInteger(1);

            List<OrderItem> orderItems = items.stream()
                    .map(item -> OrderItem.builder()
                            .id(new OrderItemId(savedOrder.getOrderId(), sequence.getAndIncrement()))
                            .order(savedOrder) // aqui o ID já existe
                            .product(item.getProduct())
                            .quantity(item.getQuantity())
                            .price(item.getPrice())
                            .build())
                    .toList();

            savedOrder.setOrderItems(new ArrayList<>(orderItems));
            orderRepo.save(savedOrder);
        }

        return savedOrder;
    }

    public void deleteById(Integer id) {
        orderRepo.deleteById(id);
    }

}
