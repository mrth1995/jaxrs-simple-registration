package io.mrth.registration.server.infrastructure;

public class TextHelper {

	public static boolean isValidEmail(String email) {
		return email.matches("^([a-zA-Z-0-9_.]+)@([a-zA-Z-0-9_.]+).([a-zA-Z]{2,5})$");
	}

	public static boolean isValidPhone(String phone) {
		return phone.matches("^(\\+62|0)([0-9]+)$");
	}
}
