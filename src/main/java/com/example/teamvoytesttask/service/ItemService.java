package com.example.teamvoytesttask.service;

import com.example.teamvoytesttask.converter.ItemConverter;
import com.example.teamvoytesttask.dto.ItemDto;
import com.example.teamvoytesttask.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;

    public ItemService(ItemRepository itemRepository, ItemConverter itemConverter) {
        this.itemRepository = itemRepository;
        this.itemConverter = itemConverter;
    }

    public List<ItemDto> getAllItems(){
        return itemConverter.toDtoList(itemRepository.findAll());
    }
}
