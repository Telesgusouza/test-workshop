package com.gustavo.demo.service.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5240938278443878864L;

	public ResourceNotFoundException(Object id) {
		super("Sourch not found. id  " + id);
	}
}
