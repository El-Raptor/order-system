package com.raptor.ordersystem.controller;

import com.raptor.ordersystem.dto.AddOrderItemDTO;
import com.raptor.ordersystem.dto.CreateOrderDTO;
import com.raptor.ordersystem.dto.OrderDTO;
import com.raptor.ordersystem.mapper.OrderItemMapper;
import com.raptor.ordersystem.mapper.OrderMapper;
import com.raptor.ordersystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/userId")
    public ResponseEntity<List<OrderDTO>> findOrdersByUserId(@RequestParam int userId) {
        var orders = orderService.findByUserId(userId);
        if (orders.isEmpty())
            return ResponseEntity.notFound().build();

        var ordersDTO = orders.stream()
                .map(OrderMapper::toDto)
                .toList();
        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable int id) {
        var order = orderService.findById(id);
        if (order == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(OrderMapper.toDto(order));
    }

    @PostMapping("/")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderDTO orderDTO) {
        var order = OrderMapper.toEntity(orderDTO);
        var savedOrder = orderService.save(order);
        URI uri = URI.create("/api/orders/" + savedOrder.getOrderId());
        return ResponseEntity.created(uri).body(OrderMapper.toDto(savedOrder));
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDTO> addItemToOrder(
            @PathVariable int id,
            @RequestBody AddOrderItemDTO dto) {
        try {
            OrderDTO orderDTO = OrderMapper.toDto(orderService.addItemToOrder(id, OrderItemMapper.toEntity(dto)));
            return ResponseEntity.ok(orderDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("{orderId}/items/{itemId}")
    public ResponseEntity<OrderDTO> deleteItemById(
            @PathVariable int orderId,
            @PathVariable int itemId) {
        var order = orderService.findById(orderId);
        if (order == null)
            return ResponseEntity.notFound().build();

        OrderDTO dto = OrderMapper.toDto(orderService.removeItemFromOrder(orderId, itemId));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("{orderId}/items")
    public ResponseEntity<OrderDTO> deleteAllItemsByOrderId(@PathVariable int orderId) {
        var order = orderService.findById(orderId);
        if (order == null)
            return ResponseEntity.notFound().build();

        OrderDTO dto = OrderMapper.toDto(orderService.removeAllItems(orderId));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        var order = orderService.findById(id);
        if (order == null)
            return ResponseEntity.notFound().build();
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
