package io.mrth.registration.server.controller;

import io.mrth.registration.server.domain.User;
import io.mrth.registration.server.model.WebUserCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
@RequestScoped
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Inject
	private EntityManager entityManager;

	@POST
	@Transactional
	public void register(WebUserCreate param) {
		LOG.info("register");
		User user = new User();
		user.setBirthDate(param.getBirthDate());
		user.setEmail(param.getEmail());
		user.setFirstName(param.getFirstName());
		user.setLastName(param.getLastName());
		user.setGender(param.getGender());
		user.setPhone(param.getPhone());

		entityManager.persist(user);
	}
}
