package com.coupond.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String phoneNumber;
}
