package com.example.teamvoytesttask.service;

import com.example.teamvoytesttask.converter.OrderConverter;
import com.example.teamvoytesttask.dto.OrderDto;
import com.example.teamvoytesttask.dto.OrderItemDto;
import com.example.teamvoytesttask.model.Order;
import com.example.teamvoytesttask.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderItemService orderItemService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderConverter orderConverter;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void whenCreateOrder_thenSaveOrder() {
        //GIVEN
        OrderDto orderDto = new OrderDto();
        when(orderConverter.toEntity(any())).thenReturn(new Order());

        //WHEN
        orderService.createOrder(orderDto);

        //THEN
        verify(orderRepository, atMostOnce()).save(any());
    }

    @Test
    public void whenGetOrder_thenReturnOrderDtoWithItems() {
        //GIVEN
        Long id = 1L;
        when(orderRepository.findById(id)).thenReturn(Optional.of(new Order()));
        when(orderConverter.toDto(any())).thenReturn(new OrderDto());
        when(orderItemService.getAllByOrderId(id)).thenReturn(List.of(new OrderItemDto()));

        //WHEN
        OrderDto actualOrder = orderService.getOrder(id);

        //THEN
        assertNotNull(actualOrder.getOrderItemDtoList());
        assertThat(actualOrder.getOrderItemDtoList()).hasSize(1);

    }

    @Test
    public void whenMarkAsPaid_thenOrderSavedAsPaid() {
        //GIVEN
        Long id = 1L;
        when(orderRepository.findById(id)).thenReturn(Optional.of(new Order()));
        ArgumentCaptor<Order> argumentCaptor = ArgumentCaptor.forClass(Order.class);

        //WHEN
        orderService.markAsPaid(id);

        //THEN
        verify(orderRepository, atMostOnce()).save(argumentCaptor.capture());
        Order actualOrder = argumentCaptor.getValue();
        assertTrue(actualOrder.isPaid());

    }

    @Test
    public void whenDeleteOrder_thenOrderAndAssociatedItemsAreDeleted() {
        //GIVEN
        Long id = 1L;

        //WHEN
        orderService.deleteOrder(id);

        //THEN
        verify(orderRepository, atMostOnce()).deleteById(id);
        verify(orderItemService, atMostOnce()).deleteAllOrderItemsByOrderId(id);
    }


    @Test
    public void whenDeleteOrders_thenOrdersAndAssociatedItemsAreDeleted() {
        //GIVEN
        List<Order> testExpiredOrders = List.of(new Order(), new Order());
        when(orderRepository.getAllByIsPaidIsFalseAndCreationTimeBefore(any())).thenReturn(testExpiredOrders);

        //WHEN
        orderService.deleteOrders();

        //THEN
        verify(orderItemService, times(testExpiredOrders.size())).deleteAllOrderItemsByOrderId(any());
        verify(orderRepository, times(testExpiredOrders.size())).deleteById(any());
    }
}
