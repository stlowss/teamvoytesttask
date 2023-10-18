package com.example.teamvoytesttask.model;

import com.example.teamvoytesttask.enumeration.Currency;
import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @Column(name = "price")
    private int price;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
