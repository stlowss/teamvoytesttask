package com.example.teamvoytesttask.dto;

import com.example.teamvoytesttask.enumeration.Currency;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ItemDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private Currency currency;
    private int price;

    public Long getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
