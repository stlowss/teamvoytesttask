package com.example.teamvoytesttask.repository;

import com.example.teamvoytesttask.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> getAllByIdIn(Collection<Long> id);
}
