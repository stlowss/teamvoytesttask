package com.example.teamvoytesttask.converter;

import com.example.teamvoytesttask.dto.OrderDto;
import com.example.teamvoytesttask.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class OrderConverterTest {
    private final OrderConverter orderConverter = new OrderConverter();

    @Test
    public void whenConvertOrderDtoToEntity_thenEntityHasMatchingFields() {
        //GIVEN
        OrderDto orderDto = createOrderDto();

        //WHEN
        Order actualOrder = orderConverter.toEntity(orderDto);

        //THEN
        assertEquals(orderDto.getId(), actualOrder.getId());
        assertEquals(orderDto.getClientId(), actualOrder.getClientId());
        assertNull(actualOrder.getCreationTime());
    }

    @Test
    public void whenConvertOrderEntityToDto_thenDtoHasMatchingFields() {
        //GIVEN
        Order order = createOrder();

        //WHEN
        OrderDto actualOrderDto = orderConverter.toDto(order);

        //THEN
        assertEquals(order.getId(), actualOrderDto.getId());
        assertEquals(order.getClientId(), actualOrderDto.getClientId());
        assertEquals(order.getCreationTime(), actualOrderDto.getCreationTime());
    }

    private Order createOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setClientId(1L);
        order.setPaid(false);
        order.setCreationTime(LocalDateTime.now());
        return order;
    }

    private OrderDto createOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setClientId(1L);
        orderDto.setPaid(false);
        orderDto.setCreationTime(LocalDateTime.now());
        return orderDto;
    }
}
