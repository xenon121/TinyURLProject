package com.tinyurl.exception;

public class TinyUrlAlreadyExistException extends Exception{

	public TinyUrlAlreadyExistException() {
		super("Tiny url already exists. please try calling get api...");
	}

	public TinyUrlAlreadyExistException(String message) {
		super(message);
	}

	
}
