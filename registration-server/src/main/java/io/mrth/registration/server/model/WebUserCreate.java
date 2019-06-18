package io.mrth.registration.server.model;

import io.mrth.registration.server.infrastructure.JsonDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WebUserCreate {
	private String firstName;
	private String lastName;
	@XmlJavaTypeAdapter(JsonDateAdapter.class)
	private Date birthDate;
	private String email;
	private Boolean gender;
	private String phone;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}
}
