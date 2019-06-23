package io.mrth.registration.server.exception;

public class RestException extends Exception {

	private int httpErrorCode;
	private String errorMessage;
	private String responseCode;

	public RestException() {
		this(400, "96", "General error");
	}

	public RestException(String errorMessage) {
		this(400, "96", errorMessage);
	}

	public RestException(String responseCode, String errorMessage) {
		this(400, errorMessage, responseCode);
	}

	public RestException(int httpErrorCode, String responseCode , String errorMessage) {
		super(errorMessage);
		this.httpErrorCode = httpErrorCode;
		this.errorMessage = errorMessage;
		this.responseCode = responseCode;
	}

	public int getHttpErrorCode() {
		return httpErrorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}
}
