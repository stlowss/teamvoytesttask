package com.example.teamvoytesttask.controller;

import com.example.teamvoytesttask.dto.OrderDto;
import com.example.teamvoytesttask.dto.OrderItemDto;
import com.example.teamvoytesttask.service.OrderItemService;
import com.example.teamvoytesttask.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderItemService orderItemService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        OrderController orderController = new OrderController(orderService, orderItemService);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void whenCreateOrderWithInvalidData_thenReturn4xxClientError() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setClientId(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whenCreateOrderWithValidData_thenReturn201Created() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setClientId(1L);
        when(orderService.createOrder(any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, "http://localhost/api/orders/1"));
    }

    @Test
    public void whenCreateOrderItemWithInvalidData_thenReturn4xxClientError() throws Exception {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setQuantity(0);
        orderItemDto.setItemId(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderItemDto)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whenCreateOrderItemWithValidData_thenReturnOk() throws Exception {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setQuantity(1);
        orderItemDto.setItemId(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderItemDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUpdateOrderItemQuantity_thenReturn204NoContent() throws Exception {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setQuantity(1);
        orderItemDto.setItemId(1L);
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/orders/1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderItemDto)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void whenDeleteOrderItem_thenReturn204NoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/orders/1/items/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void whenGetOrder_thenReturnOrderDtoWithMatchingId() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        when(orderService.getOrder(1L)).thenReturn(orderDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/1"))
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"id\": 1}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenMarkOrderAsPaid_thenReturn204NoContent() throws Exception {
        Mockito.doNothing().when(orderService).markAsPaid(1L);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/orders/1/paid"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void whenDeleteOrder_thenReturn204NoContent() throws Exception {
        Mockito.doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/orders/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
