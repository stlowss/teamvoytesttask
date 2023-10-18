package com.example.teamvoytesttask.converter;

import com.example.teamvoytesttask.dto.ItemDto;
import com.example.teamvoytesttask.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter extends Converter<Item, ItemDto> {
    @Override
    Item toEntity(ItemDto dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCurrency(dto.getCurrency());
        item.setPrice(dto.getPrice());

        return item;
    }

    @Override
    ItemDto toDto(Item entity) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(entity.getId());
        itemDto.setName(entity.getName());
        itemDto.setDescription(entity.getDescription());
        itemDto.setCurrency(entity.getCurrency());
        itemDto.setPrice(entity.getPrice());

        return itemDto;
    }
}
