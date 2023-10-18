package com.example.teamvoytesttask.converter;

import com.example.teamvoytesttask.dto.ItemDto;
import com.example.teamvoytesttask.enumeration.Currency;
import com.example.teamvoytesttask.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ItemConverterTest {
    private final ItemConverter itemConverter = new ItemConverter();

    @Test
    public void whenConvertItemDtoToEntity_thenEntityHasMatchingFields() {
        //GIVEN
        ItemDto itemDto = createItemDto();

        //WHEN
        Item actualItem = itemConverter.toEntity(itemDto);

        //THEN
        assertEquals(itemDto.getId(), actualItem.getId());
        assertEquals(itemDto.getPrice(), actualItem.getPrice());
        assertEquals(itemDto.getCurrency(), actualItem.getCurrency());
        assertEquals(itemDto.getDescription(), actualItem.getDescription());
    }

    @Test
    public void whenConvertItemEntityToDto_thenDtoHasMatchingFields() {
        //GIVEN
        Item item = createItem();

        //WHEN
        ItemDto actualItemDto = itemConverter.toDto(item);

        //THEN
        assertEquals(item.getId(), actualItemDto.getId());
        assertEquals(item.getPrice(), actualItemDto.getPrice());
        assertEquals(item.getCurrency(), actualItemDto.getCurrency());
        assertEquals(item.getDescription(), actualItemDto.getDescription());
    }


    public ItemDto createItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setPrice(20);
        itemDto.setCurrency(Currency.EUR);
        itemDto.setDescription("test");

        return itemDto;
    }

    public Item createItem() {
        Item item = new Item();
        item.setId(1L);
        item.setPrice(20);
        item.setCurrency(Currency.EUR);
        item.setDescription("test");

        return item;
    }
}
