package com.example.teamvoytesttask.repository;

import com.example.teamvoytesttask.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByIsPaidIsFalseAndCreationTimeBefore(LocalDateTime beforeTime);
    List<Order> getAllByClientId(Long clientId);
}
