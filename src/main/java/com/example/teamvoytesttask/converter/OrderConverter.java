package com.example.teamvoytesttask.converter;

import com.example.teamvoytesttask.dto.OrderDto;
import com.example.teamvoytesttask.model.Order;
import org.springframework.stereotype.Component;


@Component
public class OrderConverter extends Converter<Order, OrderDto> {

    @Override
    public Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setClientId(dto.getClientId());

        return order;
    }

    @Override
    public OrderDto toDto(Order entity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setClientId(entity.getClientId());
        orderDto.setCreationTime(entity.getCreationTime());

        return orderDto;
    }
}
