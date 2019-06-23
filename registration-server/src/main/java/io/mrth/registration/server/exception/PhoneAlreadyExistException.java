package io.mrth.registration.server.exception;

public class PhoneAlreadyExistException extends RestException{

	public PhoneAlreadyExistException() {
		this("Phone already exist");
	}

	public PhoneAlreadyExistException(String message) {
		super(message);
	}
}
