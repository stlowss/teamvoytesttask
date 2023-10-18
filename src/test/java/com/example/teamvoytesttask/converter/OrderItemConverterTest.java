package com.example.teamvoytesttask.converter;

import com.example.teamvoytesttask.dto.OrderItemDto;
import com.example.teamvoytesttask.model.OrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderItemConverterTest {
    private final OrderItemConverter orderItemConverter = new OrderItemConverter();

    @Test
    public void whenConvertOrderItemToEntity_thenEntityHasMatchingFields() {
        //GIVEN
        OrderItemDto orderItemDto = createOrderItemDto();

        //WHEN
        OrderItem actualOrderItem = orderItemConverter.toEntity(orderItemDto);

        //THEN
        assertEquals(orderItemDto.getId(), actualOrderItem.getId());
        assertEquals(orderItemDto.getItemId(), actualOrderItem.getItemId());
        assertEquals(orderItemDto.getQuantity(), actualOrderItem.getQuantity());
    }

    @Test
    public void whenConvertOrderItemEntityToDto_thenDtoHasMatchingFields() {
        //GIVEN
        OrderItem orderItem = createOrderItem();

        //WHEN
        OrderItemDto actualOrderItemDto = orderItemConverter.toDto(orderItem);

        //THEN
        assertEquals(orderItem.getId(), actualOrderItemDto.getId());
        assertEquals(orderItem.getItemId(), actualOrderItemDto.getItemId());
        assertEquals(orderItem.getQuantity(), actualOrderItemDto.getQuantity());
    }

    private OrderItemDto createOrderItemDto() {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(1L);
        orderItemDto.setItemId(2L);
        orderItemDto.setQuantity(10);

        return orderItemDto;
    }

    private OrderItem createOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setItemId(2L);
        orderItem.setQuantity(10);

        return orderItem;
    }

}
