package com.coupond.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
	
	@NotNull
	private int couponId;
	@NotBlank
	private String couponCode;
	@NotNull
	private String company;
	@NotNull
	private double price;
	@NotBlank
	private String description;
	@NotNull
	private int validity;
	
}
	