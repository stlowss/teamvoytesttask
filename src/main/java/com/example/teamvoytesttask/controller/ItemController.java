package com.example.teamvoytesttask.controller;

import com.example.teamvoytesttask.dto.ItemDto;
import com.example.teamvoytesttask.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }
}
