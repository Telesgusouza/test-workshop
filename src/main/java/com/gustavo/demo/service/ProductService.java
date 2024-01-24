package com.gustavo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gustavo.demo.entity.Product;
import com.gustavo.demo.repositories.ProductRepository;
import com.gustavo.demo.service.exception.DatabaseException;
import com.gustavo.demo.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public List<Product> findAll() {
		return repo.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Product Insert(Product obj) {
		return repo.save(obj);
	}

	public Product update(Long id, Product obj) {
		try {
			Product entity = findById(id);
			updateData(entity, obj);
			return repo.save(entity);
		} catch (EntityNotFoundException  e) {
			throw new ResourceNotFoundException(id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
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
