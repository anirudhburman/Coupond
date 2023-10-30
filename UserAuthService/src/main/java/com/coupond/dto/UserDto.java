package com.coupond.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@NotBlank
	private int userId;
	@NotBlank
	private String username;
	@NotBlank
	private String email;
	private String role;
	@NotBlank
	private String phoneNumber;
	private List<CouponDto> listOfCoupons;
	
	public UserDto(int userId, String username, String email, String role, String phoneNumber) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.role = role;
		this.phoneNumber = phoneNumber;
	}

}

