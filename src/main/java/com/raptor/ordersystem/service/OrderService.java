package com.raptor.ordersystem.service;

import com.raptor.ordersystem.entity.Order;
import com.raptor.ordersystem.entity.OrderItem;
import com.raptor.ordersystem.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public Order save(Order order) {
        List<OrderItem> items = order.getOrderItems(); // Extract items from order

        Order savedOrder = orderRepo.save(processOrder(order));
        processOrderItems(items, savedOrder);

        recalculateTotal(savedOrder); // Calculate order's total.

        return orderRepo.save(savedOrder);
    }

    /**
     * Deletes an order by its Id.
     *
     * @param id Id from order to be removed.
     */
    public void deleteById(Integer id) {
        orderRepo.deleteById(id);
    }

    /**
     * Adds a new item to an existing order.
     *
     * @param id      Order ID.
     * @param newItem New item.
     * @return <code>Order</code> Order with added item.
     */
    @Transactional
    public Order addItemToOrder(int id, OrderItem newItem) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        newItem.setOrder(order);
        order.getOrderItems().add(newItem);
        recalculateTotal(order);
        return orderRepo.save(order);
    }

    /**
     * Removes an item from order
     *
     * @param orderId Order's id that will have an item removed.
     * @param itemId  Id from Item to be removed
     */
    @Transactional
    public Order removeItemFromOrder(int orderId, int itemId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        boolean removed = order.getOrderItems()
                .removeIf(i -> i.getOrderItemId() == itemId);

        if (!removed)
            throw new RuntimeException("Item not found");

        recalculateTotal(order);
        return order;
    }

    /**
     * Creates an order without items.
     *
     * @param order with items that will be cloned with no items.
     * @return <code>Order</code> Order created.
     */
    private Order processOrder(Order order) {

        // Creates an Order without items
        order = Order.builder()
                .user(order.getUser())
                .orderDate(order.getOrderDate())
                .build();

        return order;
    }

    /**
     * Set orders to list of items and set items to given order.
     *
     * @param items Order items to be set
     * @param order Order to be set
     */
    private void processOrderItems(List<OrderItem> items, Order order) {
        items.forEach(i -> i.setOrder(order));
        order.setOrderItems(new ArrayList<>(items));
    }

    /**
     * Calculate order's total based on its items.
     *
     * @param order order to have its total price calculated.
     */
    private void recalculateTotal(Order order) {
        List<OrderItem> items = order.getOrderItems();
        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        order.setTotal(total);
    }
}
