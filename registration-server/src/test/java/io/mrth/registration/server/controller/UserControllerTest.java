package io.mrth.registration.server.controller;

import io.mrth.registration.server.domain.User;
import io.mrth.registration.server.exception.EmailAlreadyExistException;
import io.mrth.registration.server.exception.InvalidEmailException;
import io.mrth.registration.server.exception.InvalidPhoneException;
import io.mrth.registration.server.exception.PhoneAlreadyExistException;
import io.mrth.registration.server.model.WebUserCreate;
import io.mrth.registration.server.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserController userController;

	@Test
	public void register() throws PhoneAlreadyExistException, EmailAlreadyExistException, InvalidEmailException, InvalidPhoneException {
		WebUserCreate webUserCreate = new WebUserCreate("Tony", "Start", new GregorianCalendar(1995, Calendar.MARCH, 1).getTime(),
				"tony@stark.com", true, "087825169069");
		User expected = new User();
		expected.setPhone("087825169069");
		expected.setGender(true);
		expected.setLastName("Start");
		expected.setFirstName("Tony");
		expected.setEmail("tony@stark.com");
		expected.setBirthDate(new GregorianCalendar(1995, Calendar.MARCH, 1).getTime());

		Mockito.when(userRepository.findByEmail(webUserCreate.getEmail())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(webUserCreate.getPhone())).thenReturn(null);

		AtomicReference<User> reference = new AtomicReference<>();

		Mockito.doAnswer(invocationOnMock -> {
			User u = (User) invocationOnMock.getArguments()[0];
			reference.set(u);
			return null;
		}).when(userRepository).store(any(User.class));

		userController.register(webUserCreate);

		User persistedUser = reference.get();
		Assert.assertEquals(persistedUser.getFirstName(), expected.getFirstName());
		Assert.assertEquals(persistedUser.getLastName(), expected.getLastName());
		Assert.assertEquals(persistedUser.getEmail(), expected.getEmail());
		Assert.assertEquals(persistedUser.getPhone(), expected.getPhone());
		Assert.assertEquals(persistedUser.getBirthDate(), expected.getBirthDate());
		Assert.assertEquals(persistedUser.getGender(), expected.getGender());
	}

	@Test(expected = EmailAlreadyExistException.class)
	public void register_emailAlreadyExist() throws PhoneAlreadyExistException, EmailAlreadyExistException, InvalidEmailException, InvalidPhoneException {
		WebUserCreate param = new WebUserCreate("Tony", "Start", new GregorianCalendar(1995, Calendar.MARCH, 1).getTime(),
				"tony@stark.com", true, "087825169069");
		User existUser = new User();
		existUser.setPhone("087825169069");
		existUser.setGender(true);
		existUser.setLastName("Start");
		existUser.setFirstName("Tony");
		existUser.setEmail("tony@stark.com");
		existUser.setBirthDate(new GregorianCalendar(1995, Calendar.MARCH, 1).getTime());

		Mockito.when(userRepository.findByEmail(param.getEmail())).thenReturn(existUser);
		userController.register(param);
	}

	@Test(expected = PhoneAlreadyExistException.class)
	public void register_phoneAlreadyExist() throws PhoneAlreadyExistException, EmailAlreadyExistException, InvalidEmailException, InvalidPhoneException {
		WebUserCreate param = new WebUserCreate("Tony", "Start", new GregorianCalendar(1995, Calendar.MARCH, 1).getTime(),
				"tony@stark.com", true, "087825169069");
		User existUser = new User();
		existUser.setPhone("087825169069");
		existUser.setGender(true);
		existUser.setLastName("Start");
		existUser.setFirstName("Tony");
		existUser.setEmail("tony@stark.com");
		existUser.setBirthDate(new GregorianCalendar(1995, Calendar.MARCH, 1).getTime());

		Mockito.when(userRepository.findByPhone(param.getPhone())).thenReturn(existUser);
		userController.register(param);
	}

	@Test(expected = InvalidEmailException.class)
	public void register_invalidEmail() throws InvalidPhoneException, EmailAlreadyExistException, InvalidEmailException, PhoneAlreadyExistException {
		WebUserCreate param = new WebUserCreate("Tony", "Start", new GregorianCalendar(1995, Calendar.MARCH, 1).getTime(),
				"@stark.com", true, "087825169069");
		Mockito.when(userRepository.findByPhone(param.getPhone())).thenReturn(null);
		Mockito.when(userRepository.findByEmail(param.getEmail())).thenReturn(null);
		userController.register(param);
	}

	@Test(expected = InvalidPhoneException.class)
	public void register_invalidPhone() throws InvalidPhoneException, EmailAlreadyExistException, InvalidEmailException, PhoneAlreadyExistException {
		WebUserCreate param = new WebUserCreate("Tony", "Start", new GregorianCalendar(1995, Calendar.MARCH, 1).getTime(),
				"tony.@stark.com", true, "+987825169069");
		Mockito.when(userRepository.findByPhone(param.getPhone())).thenReturn(null);
		Mockito.when(userRepository.findByEmail(param.getEmail())).thenReturn(null);
		userController.register(param);
	}
}
