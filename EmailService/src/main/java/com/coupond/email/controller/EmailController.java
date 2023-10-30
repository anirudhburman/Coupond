package com.coupond.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupond.email.service.EmailService;

@RestController
@RequestMapping("/sendMail")
public class EmailController {

	@Autowired
	EmailService service;

	@GetMapping("/{to}/{subject}/{body}")
	public String sendMail(@PathVariable String to,@PathVariable String subject,@PathVariable String body)
	{
		return service.sendEmail(to, subject, body);
	}
}
