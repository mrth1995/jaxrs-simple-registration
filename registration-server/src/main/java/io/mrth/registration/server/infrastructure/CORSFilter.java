package io.mrth.registration.server.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {
	private static final Logger LOG = LoggerFactory.getLogger(CORSFilter.class);

	@Override
	public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add(
				"Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add(
				"Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add(
				"Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization");
		responseContext.getHeaders().add(
				"Access-Control-Allow-Methods",
				"GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}
}
