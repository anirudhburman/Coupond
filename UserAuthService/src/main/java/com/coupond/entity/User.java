package com.coupond.entity;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
	
	@Transient
    public static final String SEQUENCE_NAME = "seq";
	
	@MongoId
	private int userId;
	@NotBlank
	@Indexed(unique = true)
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	@Email
	@Indexed(unique = true)
	private String email;
	private String role;
	@NotBlank
	@Indexed(unique = true)
	@Pattern(regexp = "^[789]\\d{9}$", message = "Invalid phone number format")
	private String phoneNumber;
	private List<String> listOfCoupons;
	
	public User(String username, String password, String email, String role, String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.phoneNumber = phoneNumber;
	}

}