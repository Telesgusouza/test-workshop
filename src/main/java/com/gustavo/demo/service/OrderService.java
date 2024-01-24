package com.gustavo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gustavo.demo.entity.Order;
import com.gustavo.demo.repositories.OrderRepository;
import com.gustavo.demo.service.exception.DatabaseException;
import com.gustavo.demo.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	public List<Order> findAll() {
		return repo.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Order insert(Order obj) {
		return repo.save(obj);
	}

	public Order update(Long id, Order obj) {
		try {
			Order entity = findById(id);
			updateData(entity, obj);
			return entity;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateData(Order entity, Order obj) {

	}

	public void delete(Long id) {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
