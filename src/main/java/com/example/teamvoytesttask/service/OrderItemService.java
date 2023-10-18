package com.example.teamvoytesttask.service;

import com.example.teamvoytesttask.exception.ItemAlreadyInOrderException;
import com.example.teamvoytesttask.converter.OrderItemConverter;
import com.example.teamvoytesttask.dto.OrderItemDto;
import com.example.teamvoytesttask.model.OrderItem;
import com.example.teamvoytesttask.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemConverter orderItemConverter;
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemConverter orderItemConverter, OrderItemRepository orderItemRepository) {
        this.orderItemConverter = orderItemConverter;
        this.orderItemRepository = orderItemRepository;
    }

    public void addItemToOrder(OrderItemDto orderItemDto, Long orderId) {
        Optional<OrderItem> sameItemOrder = orderItemRepository.findByOrderIdAndItemId(orderId, orderItemDto.getItemId());
        if(sameItemOrder.isPresent()){
            throw new ItemAlreadyInOrderException("This item is already in order.");
        }
        OrderItem orderItem = orderItemConverter.toEntity(orderItemDto);
        orderItem.setOrderId(orderId);

        orderItemRepository.save(orderItem);
    }

    public List<OrderItemDto> getAllByOrderId(Long orderId) {
        List<OrderItem> allByOrderId = orderItemRepository.getAllByOrderId(orderId);
        return orderItemConverter.toDtoList(allByOrderId);
    }

    public void updateOrderItemQuantity(OrderItemDto orderItemDto) {
        OrderItem orderItem = getOrderItemById(orderItemDto);
        orderItem.setQuantity(orderItemDto.getQuantity());

        orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    public void deleteAllOrderItemsByOrderId(Long ordersId) {
        orderItemRepository.deleteAllByOrderId(ordersId);
    }

    private OrderItem getOrderItemById(OrderItemDto orderItemDto) {
        return orderItemRepository.findById(orderItemDto.getId()).orElseThrow();
    }
}
