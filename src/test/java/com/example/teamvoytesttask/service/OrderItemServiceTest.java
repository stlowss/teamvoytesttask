package com.example.teamvoytesttask.service;

import com.example.teamvoytesttask.converter.OrderItemConverter;
import com.example.teamvoytesttask.dto.OrderItemDto;
import com.example.teamvoytesttask.exception.ItemAlreadyInOrderException;
import com.example.teamvoytesttask.model.OrderItem;
import com.example.teamvoytesttask.repository.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderItemServiceTest {
    @Mock
    private OrderItemConverter orderItemConverter;
    @Mock
    private OrderItemRepository orderItemRepository;
    @InjectMocks
    private OrderItemService orderItemService;

    @Test
    public void whenAddNewItemToOrder_thenSaveOrderItemWithOrderId() {
        //GIVEN
        Long orderId = 1L;
        when(orderItemRepository.findByOrderIdAndItemId(orderId, 1L)).thenReturn(Optional.empty());
        when(orderItemConverter.toEntity(any())).thenReturn(new OrderItem());
        ArgumentCaptor<OrderItem> argumentCaptor = ArgumentCaptor.forClass(OrderItem.class);

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setItemId(1L);

        //WHEN
        orderItemService.addItemToOrder(orderItemDto, orderId);

        //THEN
        verify(orderItemRepository, atMostOnce()).save(argumentCaptor.capture());
        OrderItem actualOrderItem = argumentCaptor.getValue();
        assertEquals(orderId, actualOrderItem.getOrderId());
    }

    @Test
    public void whenAddExistingItemToOrder_thenItemAlreadyInOrderException() {
        //GIVEN
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(1L);
        orderItemDto.setItemId(1L);
        when(orderItemRepository.findByOrderIdAndItemId(1L, orderItemDto.getItemId()))
                .thenReturn(Optional.of(new OrderItem()));

        //WHEN

        //THEN
        assertThrows(ItemAlreadyInOrderException.class, () -> orderItemService.addItemToOrder(orderItemDto, 1L));
    }

    @Test
    public void whenUpdateOrderItemQuantity_thenSaveUpdatedQuantity() {
        //GIVEN
        when(orderItemRepository.findById(any())).thenReturn(Optional.of(new OrderItem()));
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setQuantity(30);

        //WHEN
        orderItemService.updateOrderItemQuantity(orderItemDto);

        //THEN
        ArgumentCaptor<OrderItem> argumentCaptor = ArgumentCaptor.forClass(OrderItem.class);
        verify(orderItemRepository, atMostOnce()).save(argumentCaptor.capture());
        OrderItem actualOrderItem = argumentCaptor.getValue();
        assertEquals(orderItemDto.getQuantity(), actualOrderItem.getQuantity());
    }
}
