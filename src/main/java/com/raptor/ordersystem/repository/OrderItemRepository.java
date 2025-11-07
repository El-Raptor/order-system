package com.raptor.ordersystem.repository;

import com.raptor.ordersystem.entity.OrderItem;
import com.raptor.ordersystem.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
