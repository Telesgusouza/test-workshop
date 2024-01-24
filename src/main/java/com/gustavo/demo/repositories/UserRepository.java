package com.gustavo.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.demo.entity.Product;
import com.gustavo.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
