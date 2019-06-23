package io.mrth.registration.server.exception;

public class InvalidPhoneException extends RestException {
	public InvalidPhoneException() {
		this("Invalid phone format");
	}

	public InvalidPhoneException(String message) {
		super(message);
	}
}
