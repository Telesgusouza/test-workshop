package com.gustavo.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
