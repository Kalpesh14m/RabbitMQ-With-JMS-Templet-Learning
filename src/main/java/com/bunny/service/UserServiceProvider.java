package com.bunny.service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunny.model.Mail;
import com.bunny.model.User;
import com.bunny.model.dto.UserRegistrationDTO;
import com.bunny.repo.custom.userRepository;
import com.bunny.utils.DateUtil;
import com.bunny.utils.EmailService;
import com.bunny.utils.RabbitMQSender;
import com.bunny.utils.exception.BadRequestError;

@Service
public class UserServiceProvider implements userService {

	@Autowired
	private userRepository userRepository;

	@Autowired
	private RabbitMQSender rabbitMQSender;

	@Autowired
	private Mail mail;

	@Override
	public Optional<User> registerUser(UserRegistrationDTO request) {
		Optional<User> registeredUser = userRepository.findByEmail(request.getEmail());
		if (registeredUser.isPresent()) {
			throw new BadRequestError("User already registerd...!!!");
		}
		Optional<User> maybeUser = Optional.ofNullable(userRepository.save(new User(request)));
		String msg = "Successfull Register: " + request.getFirstName();
		mail.setTo(request.getEmail());
		mail.setFrom(EmailService.SENDER_EMAIL_ID);
		mail.setSentDate(Date.from(DateUtil.today().atZone(ZoneId.systemDefault()).toInstant()));
		mail.setSubject("Registration Successfull..!!!");
		mail.setContent(msg);
		rabbitMQSender.send(mail);
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
