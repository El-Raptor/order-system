package com.raptor.ordersystem.repository;

import com.raptor.ordersystem.entity.Order;
import com.raptor.ordersystem.entity.OrderItem;
import com.raptor.ordersystem.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
    @Query("select * from order_items oi where oi.order_id = ?1",
            nativeQuery = true)
    List<OrderItem> findAllByOrderId(int orderId);

    @Query("delete from OrderItem where order.orderId = ?1")
    void deleteAllByOrderId(Integer orderId);
}
