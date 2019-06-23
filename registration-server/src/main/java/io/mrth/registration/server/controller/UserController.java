package io.mrth.registration.server.controller;

import io.mrth.registration.server.domain.User;
import io.mrth.registration.server.exception.EmailAlreadyExistException;
import io.mrth.registration.server.exception.InvalidEmailException;
import io.mrth.registration.server.exception.InvalidPhoneException;
import io.mrth.registration.server.exception.PhoneAlreadyExistException;
import io.mrth.registration.server.infrastructure.TextHelper;
import io.mrth.registration.server.model.WebUserCreate;
import io.mrth.registration.server.repository.UserRepository;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Inject
	private UserRepository userRepository;

	@POST
	@Transactional
	public void register(WebUserCreate param) throws EmailAlreadyExistException, PhoneAlreadyExistException, InvalidEmailException, InvalidPhoneException {
		LOG.info("register");

		User existingUser = userRepository.findByEmail(param.getEmail());
		if (existingUser != null) {
			throw new EmailAlreadyExistException();
		}
		existingUser = userRepository.findByPhone(param.getPhone());
		if (existingUser != null) {
			throw new PhoneAlreadyExistException();
		}
		if (!TextHelper.isValidEmail(param.getEmail())) {
			throw new InvalidEmailException();
		}
		if (!TextHelper.isValidPhone(param.getPhone())) {
			throw new InvalidPhoneException();
		}
		User user = new User();
		user.setBirthDate(param.getBirthDate());
		user.setEmail(param.getEmail());
		user.setFirstName(param.getFirstName());
		user.setLastName(param.getLastName());
		user.setGender(param.getGender());
		user.setPhone(param.getPhone());

		userRepository.store(user);
	}
}
