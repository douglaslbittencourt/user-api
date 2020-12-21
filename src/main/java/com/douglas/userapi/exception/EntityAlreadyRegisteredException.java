package com.douglas.userapi.exception;

public class EntityAlreadyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityAlreadyRegisteredException(String message) {
        super(message);
    }
}
