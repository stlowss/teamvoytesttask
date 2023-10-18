package com.example.teamvoytesttask.controller;

import com.example.teamvoytesttask.dto.OrderDto;
import com.example.teamvoytesttask.dto.OrderItemDto;
import com.example.teamvoytesttask.service.OrderItemService;
import com.example.teamvoytesttask.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public OrderController(OrderService orderService,
                           OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody @Valid OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);

        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        URI location = builder.path("/{id}").buildAndExpand(createdOrder.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("{id}/items")
    public ResponseEntity<Void> addOrderItem(@PathVariable(name = "id") Long id,
                                             @RequestBody @Valid OrderItemDto orderItemDto) {
        orderItemService.addItemToOrder(orderItemDto, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/items")
    public ResponseEntity<Void> updateOrderItemQuantity(@PathVariable(name = "id") Long id,
                                                        @RequestBody  @Valid OrderItemDto orderItemDto) {
        orderItemService.updateOrderItemQuantity(orderItemDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{order-id}/items/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public OrderDto getOrder(@PathVariable(name = "id") Long id) {
        return orderService.getOrder(id);
    }

    @PatchMapping("{id}/paid")
    public ResponseEntity<Void> markOrderAsPaid(@PathVariable Long id) {
        orderService.markAsPaid(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable(name = "id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
