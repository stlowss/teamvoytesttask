package com.example.teamvoytesttask.controller;

import com.example.teamvoytesttask.dto.ClientDto;
import com.example.teamvoytesttask.dto.OrderDto;
import com.example.teamvoytesttask.service.ClientService;
import com.example.teamvoytesttask.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private final OrderService orderService;
    private final ClientService clientService;

    public ClientController(OrderService orderService,
                            ClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @GetMapping("/{client-id}/orders")
    public List<OrderDto> getClientOrders(@PathVariable(name = "client-id") Long clientId) {
        return orderService.getClientOrders(clientId);
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody @Valid ClientDto clientDto) {
         clientService.createClient(clientDto);

         return ResponseEntity.ok().build();
    }
}
