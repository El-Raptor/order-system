package com.raptor.ordersystem.controller;

import com.raptor.ordersystem.dto.AddOrderItemDTO;
import com.raptor.ordersystem.dto.OrderItemDTO;
import com.raptor.ordersystem.entity.OrderItem;
import com.raptor.ordersystem.mapper.OrderItemMapper;
import com.raptor.ordersystem.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@Deprecated
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItemDTO>> getOrderItemByOrderId(@PathVariable int orderId) {
        var orderItems = orderItemService.findAllByOrderId(orderId);
        var dtos = new ArrayList<>(orderItems.stream().map(OrderItemMapper::toDto).toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<OrderItemDTO> addOrderItem(@RequestBody AddOrderItemDTO dto) {
        var orderItem = orderItemService.addItem(dto);
        URI uri = URI.create("/api/order-items/" + orderItem.getOrder().getOrderId());
        return ResponseEntity.created(uri).body(OrderItemMapper.toDto(orderItem));
    }

    @Transactional
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteItemsByOrderId(@PathVariable int orderId) {
        var items = orderItemService.findAllByOrderId(orderId);
        if (items.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        orderItemService.deleteAllByOrderId(orderId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{orderId}/item")
    public ResponseEntity<Void> deleteItemById(@PathVariable int orderId, @RequestParam int itemId) {
        OrderItem item = orderItemService.findById(itemId);
        if (item == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        orderItemService.deleteById(itemId);
        return ResponseEntity.noContent().build();
    }
}
