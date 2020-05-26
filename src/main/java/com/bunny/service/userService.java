package com.bunny.service;

import java.util.List;
import java.util.Optional;

import com.bunny.model.User;
import com.bunny.model.dto.UserRegistrationDTO;

public interface userService {

	public Optional<User> registerUser(UserRegistrationDTO request);

	public List<User> getUsers(String firstName, String lastName, String email, String registerDate,
			String mobileNumber);

	public String updateUser(List<User> users);

	public boolean deleteSource(String userId);

}
