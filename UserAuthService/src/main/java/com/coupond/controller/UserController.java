package com.coupond.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupond.dto.CouponDto;
import com.coupond.dto.RegisterDto;
import com.coupond.dto.UpdateUserDto;
import com.coupond.dto.UserDto;
import com.coupond.exception.ResourceNotFoundException;
import com.coupond.exception.UserAlreadyExistsException;
import com.coupond.exception.UserNotFoundException;
import com.coupond.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/registerUser")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegisterDto registeringUser) throws UserAlreadyExistsException {
		return new ResponseEntity<>(userService.registerUser(registeringUser), HttpStatus.CREATED);
	}
	
	@PostMapping("/registerAdmin")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<UserDto> registerAdmin(@Valid @RequestBody RegisterDto registeringUser) throws UserAlreadyExistsException {
		return new ResponseEntity<>(userService.registerAdmin(registeringUser), HttpStatus.CREATED);
	}

	@GetMapping("/getAllUsers")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/getUserByUsername/{username}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<UserDto> getUserByUsername(@Valid @PathVariable String username) throws UserNotFoundException {
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}

	@GetMapping("/getUserById/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<UserDto> getUserById(@Valid @PathVariable int id) throws UserNotFoundException {
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
	}

	@PutMapping("/updateUser")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UpdateUserDto user) throws UserNotFoundException {
		return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
	}

	@DeleteMapping("/deleteUserByUsername/{username}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<String> deleteUserByUsername(@Valid @PathVariable String username) throws UserNotFoundException {
		return new ResponseEntity<>(userService.deleteUserByUsername(username), HttpStatus.OK);
	}

	@DeleteMapping("/deleteUserById/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<String> deleteUserById(@Valid @PathVariable int id) throws UserNotFoundException {
		return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
	}

	@GetMapping("/getCouponsByUserId/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<List<String>> getCouponsByUserId(@Valid @PathVariable int id) throws UserNotFoundException {
		return new ResponseEntity<>(userService.getCouponsByUserId(id), HttpStatus.OK);
	}

	@PostMapping("/buyCoupon/{username}")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<List<CouponDto>> buyCoupon(@Valid @PathVariable String username, @Valid @RequestBody List<String> coupons)
			throws ResourceNotFoundException, UserNotFoundException {
		return new ResponseEntity<>(userService.buyCoupons(username, coupons), HttpStatus.OK);
	}

}
