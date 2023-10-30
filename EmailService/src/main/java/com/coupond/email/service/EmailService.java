package com.coupond.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public String sendEmail(String to,String subject,String body){
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		mailSender.send(mail);
		return "Mail Send Successfully";
	}
}
