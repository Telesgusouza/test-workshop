package com.gustavo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gustavo.demo.entity.User;
import com.gustavo.demo.repositories.UserRepository;
import com.gustavo.demo.service.exception.DatabaseException;
import com.gustavo.demo.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		obj = repo.save(obj);
		return obj;
	}

	public User update(Long id, User obj) {
		try {
			User entity = findById(id);
			updateData(entity, obj);
			return repo.save(entity);
		} catch (EntityNotFoundException  e) {
			throw new ResourceNotFoundException(id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
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
