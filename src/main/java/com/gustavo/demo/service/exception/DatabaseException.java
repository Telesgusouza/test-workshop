package com.gustavo.demo.service.exception;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 6385057173377861976L;

	public DatabaseException(String msg) {
		super(msg);
	}
	
}
