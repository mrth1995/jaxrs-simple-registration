package io.mrth.registration.server.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "web_user", indexes = {
		@Index(name = "idx_phone", columnList = "phone", unique = true),
		@Index(name = "idx_email", columnList = "email", unique = true)
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 32)
	private String id;

	@Column(name = "first_name", length = 64, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 64, nullable = false)
	private String lastName;

	@Column(length = 13, nullable = false)
	private String phone;

	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column
	private Boolean gender;

	@Column(length = 32, nullable = false)
	private String email;

	public User() {
		this.id = UUID.randomUUID().toString().replace("-","");
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Boolean getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
