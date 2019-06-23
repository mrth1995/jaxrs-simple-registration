package io.mrth.registration.server.exception;

public class InvalidEmailException extends RestException {
	public InvalidEmailException() {
		this("Invalid email format");
	}

	public InvalidEmailException(String message) {
		super(message);
	}
}
