package com.example.teamvoytesttask.controller;

import com.example.teamvoytesttask.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ItemService itemService;


    @BeforeEach
    void setUp() {
        ItemController itemController = new ItemController(itemService);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void whenGetAllItems_thenReturnItemListAndStatus200() throws Exception {
        when(itemService.getAllItems()).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }
}
