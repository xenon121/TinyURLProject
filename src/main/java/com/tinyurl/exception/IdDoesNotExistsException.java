package com.tinyurl.exception;

public class IdDoesNotExistsException extends Exception {

	public IdDoesNotExistsException() {
		super("Id trying to access does not exists...");
	}

	public IdDoesNotExistsException(String message) {
		super(message);
	}

	
}
