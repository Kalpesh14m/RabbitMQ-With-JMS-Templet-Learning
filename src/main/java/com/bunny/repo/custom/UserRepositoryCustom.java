package com.bunny.repo.custom;

import java.util.List;

import com.bunny.model.User;

public interface UserRepositoryCustom {
	public List<User> getUsers(String firstName, String lastName, String email, String registerDate,
			String mobileNumber);

}
