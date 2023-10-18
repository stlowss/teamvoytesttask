package com.example.teamvoytesttask.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private Long id;
    private List<OrderItemDto> orderItemDtoList;
    private boolean isPaid;
    @NotNull
    private Long clientId;
    private LocalDateTime creationTime;

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemDto> getOrderItemDtoList() {
        return orderItemDtoList;
    }

    public void setOrderItemsList(List<OrderItemDto> itemsIdList) {
        this.orderItemDtoList = itemsIdList;
    }
}
