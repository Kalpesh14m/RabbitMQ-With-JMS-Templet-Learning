package com.bunny.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bunny.model.Mail;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	public static final String SENDER_EMAIL_ID = System.getenv("email");

	public void sendSimpleMessage(final Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		message.setTo(mail.getTo());
		message.setFrom(mail.getFrom());
		message.setSentDate(mail.getSentDate());
		emailSender.send(message);
	}
}
