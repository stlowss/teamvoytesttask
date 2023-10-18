package com.example.teamvoytesttask.converter;

import com.example.teamvoytesttask.dto.OrderItemDto;
import com.example.teamvoytesttask.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter extends Converter<OrderItem, OrderItemDto> {

    @Override
    public OrderItem toEntity(OrderItemDto dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(dto.getId());
        orderItem.setItemId(dto.getItemId());
        orderItem.setQuantity(dto.getQuantity());

        return orderItem;
    }

    @Override
    public OrderItemDto toDto(OrderItem entity) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(entity.getId());
        orderItemDto.setItemId(entity.getItemId());
        orderItemDto.setQuantity(entity.getQuantity());

        return orderItemDto;
    }
}
