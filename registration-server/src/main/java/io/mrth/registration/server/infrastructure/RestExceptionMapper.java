package io.mrth.registration.server.infrastructure;

import io.mrth.registration.server.exception.RestException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {
	@Override
	public Response toResponse(RestException e) {
		return Response.status(e.getHttpErrorCode())
				.header("Content-Type", "text/plain")
				.entity(e.getErrorMessage())
				.build();
	}
}
