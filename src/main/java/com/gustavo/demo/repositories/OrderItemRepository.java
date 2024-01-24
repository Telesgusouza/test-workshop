package com.gustavo.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.demo.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
