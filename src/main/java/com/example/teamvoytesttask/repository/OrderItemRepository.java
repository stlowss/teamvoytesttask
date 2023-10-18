package com.example.teamvoytesttask.repository;

import com.example.teamvoytesttask.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByOrderIdAndItemId(Long orderId, Long itemId);
    void deleteAllByOrderId(Long orderId);
    List<OrderItem> getAllByOrderId(Long orderId);
}
