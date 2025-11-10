package com.raptor.ordersystem.service;

import com.raptor.ordersystem.dto.CreateOrderDTO;
import com.raptor.ordersystem.entity.Order;
import com.raptor.ordersystem.mapper.OrderMapper;
import com.raptor.ordersystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> findByUserId(Integer id) {
        return orderRepo.findByUserId(id);
    }

    public Order findById(Integer id) {
        return orderRepo.findById(id).orElse(null);
    }

    public Order save(CreateOrderDTO orderDTO) {
        return orderRepo.save(OrderMapper.toEntity(orderDTO));
    }

    public void deleteById(Integer id) {
        orderRepo.deleteById(id);
    }

}
