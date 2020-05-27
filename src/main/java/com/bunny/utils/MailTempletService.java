package com.bunny.utils;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bunny.model.Mail;
import com.bunny.model.dto.UserRegistrationDTO;

@Component
public class MailTempletService {

	@Autowired
	private RabbitMQSender rabbitMQSender;

	@Autowired
	private Mail mail;

	private String RegistrationTemplate = "";

	public void getTemplate(UserRegistrationDTO request) throws IOException {
		if (RegistrationTemplate.equals("")) {
			RegistrationTemplate = Template.readContentFromTemplet();
		}
		RegistrationTemplate = RegistrationTemplate.replaceAll(Pattern.quote("$%name%"), request.getFirstName());
		mail.setTo(request.getEmail());
		mail.setFrom(EmailService.SENDER_EMAIL_ID);
		mail.setSubject(request.getFirstName() + ", Registration Successful");
		mail.setContent(RegistrationTemplate);
		mail.setSentDate(Date.from(DateUtil.today().atZone(ZoneId.systemDefault()).toInstant()));
		rabbitMQSender.send(mail);
	}

}
