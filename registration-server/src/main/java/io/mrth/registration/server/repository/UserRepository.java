package io.mrth.registration.server.repository;

import io.mrth.registration.server.domain.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;

@Dependent
public class UserRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public void store(User user) {
		entityManager.persist(user);
	}

	public User findByEmail(String email) {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("email", email);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User findByPhone(String phone) {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findByPhone", User.class);
		query.setParameter("phone", phone);
		query.setMaxResults(1);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
