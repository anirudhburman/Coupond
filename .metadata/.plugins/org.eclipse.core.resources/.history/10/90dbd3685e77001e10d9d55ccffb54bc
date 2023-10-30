package com.coupond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupond.dto.LoginDto;
import com.coupond.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/app")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@Valid @RequestBody LoginDto loginRequest) {
		return userService.loginUser(loginRequest);	
	}
}

