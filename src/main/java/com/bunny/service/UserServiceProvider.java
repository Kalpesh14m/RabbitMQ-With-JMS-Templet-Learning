package com.bunny.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunny.model.User;
import com.bunny.model.dto.UserRegistrationDTO;
import com.bunny.repo.custom.userRepository;
import com.bunny.utils.DateUtil;
import com.bunny.utils.MailTempletService;
import com.bunny.utils.exception.BadRequestError;

@Service
public class UserServiceProvider implements userService {

	@Autowired
	private userRepository userRepository;

	@Autowired
	private MailTempletService mailTempletService;

	@Override
	public Optional<User> registerUser(UserRegistrationDTO request) {
		Optional<User> registeredUser = userRepository.findByEmail(request.getEmail());
		if (registeredUser.isPresent()) {
			throw new BadRequestError("User already registerd...!!!");
		}
		Optional<User> maybeUser = Optional.ofNullable(userRepository.save(new User(request)));
		try {
			mailTempletService.getTemplate(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return maybeUser;
	}

	@Override
	public List<User> getUsers(String firstName, String lastName, String email, String registerDate,
			String mobileNumber) {
		return userRepository.getUsers(firstName, lastName, email, registerDate, mobileNumber);
	}

	@Override
	public String updateUser(List<User> users) {
		for (User u : users) {
			u.setUpdateDateTime(DateUtil.today());
			userRepository.save(u);
		}
		return "User Updated Successfully...!!!";
	}

	@Override
	public boolean deleteSource(String userId) {
		Optional<User> maybeUser = userRepository.findById(Long.parseLong(userId));
		if (maybeUser.isPresent()) {
			userRepository.deleteById(maybeUser.get().getId());
			return true;
		}
		return false;
	}

}
