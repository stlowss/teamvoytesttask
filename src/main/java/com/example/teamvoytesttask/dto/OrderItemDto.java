package com.example.teamvoytesttask.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemDto {
    private Long id;
    @NotNull
    private Long itemId;

    @Min(value = 1)
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
