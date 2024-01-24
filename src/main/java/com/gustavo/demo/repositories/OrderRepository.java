package com.gustavo.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
