package io.mrth.registration.server.exception;

public class EmailAlreadyExistException extends RestException {

	public EmailAlreadyExistException() {
		this("Email already exist");
	}

	public EmailAlreadyExistException(String message) {
		super(message);
	}
}
