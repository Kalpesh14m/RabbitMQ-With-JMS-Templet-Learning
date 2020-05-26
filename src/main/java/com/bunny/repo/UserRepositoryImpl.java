package com.bunny.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.bunny.model.User;
import com.bunny.repo.custom.UserRepositoryCustom;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getUsers(String firstName, String lastName, String email, String registerDate,
			String mobileNumber) {
		String sql = "select u from User u where 1=1 ";
		if (!StringUtils.isEmpty(firstName)) {
			sql += String.format("and u.firstName = '%s' ", firstName);
		}
		if (!StringUtils.isEmpty(lastName)) {
			sql += String.format("and u.lastName = '%s' ", lastName);
		}
		if (!StringUtils.isEmpty(email)) {
			sql += String.format("and u.email = '%s' ", email);
		}
		if (!StringUtils.isEmpty(registerDate)) {
			sql += String.format("and u.createdDate = '%s' ", registerDate);
		}
		if (!StringUtils.isEmpty(mobileNumber)) {
			sql += String.format("and u.mobileNumber = '%s' ", mobileNumber);
		}
		return entityManager.createQuery(sql, User.class).getResultList();
	}

}
