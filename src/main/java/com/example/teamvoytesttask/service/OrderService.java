package com.example.teamvoytesttask.service;

import com.example.teamvoytesttask.converter.OrderConverter;
import com.example.teamvoytesttask.dto.OrderDto;
import com.example.teamvoytesttask.model.Order;
import com.example.teamvoytesttask.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final OrderItemService orderItemService;


    public OrderService(OrderRepository orderRepository,
                        OrderConverter orderConverter,
                        OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.orderItemService = orderItemService;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderConverter.toEntity(orderDto);
        order = orderRepository.save(order);

        return orderConverter.toDto(order);
    }

    public OrderDto getOrder(Long id) {
        Order orderById = getOrderById(id);
        OrderDto orderDto = orderConverter.toDto(orderById);
        orderDto.setOrderItemsList(orderItemService.getAllByOrderId(id));

        return orderDto;
    }

    public void markAsPaid(Long id) {
        Order order = getOrderById(id);
        order.setPaid(true);
        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderItemService.deleteAllOrderItemsByOrderId(id);
        orderRepository.deleteById(id);
    }

    public List<OrderDto> getClientOrders(Long clientId) {
        List<Order> allByClientId = orderRepository.getAllByClientId(clientId);
        return orderConverter.toDtoList(allByClientId);
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.MINUTES)
    @Transactional
    public void deleteOrders() {
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
        List<Order> unpaidExpiredOrders = orderRepository.getAllByIsPaidIsFalseAndCreationTimeBefore(tenMinutesAgo);
        unpaidExpiredOrders.forEach(order -> deleteOrder(order.getId()));
    }

    private Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Order with id %s doesnt exist".formatted(id)));
    }
}
