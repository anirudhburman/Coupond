package com.coupond.enitity;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "coupons")
public class Coupon {

	@Transient
    public static final String SEQUENCE_NAME = "seq";
	
	@MongoId
	private int couponId;
	@NotBlank
	@Indexed(unique = true)
	private String couponCode;
	@NotBlank
	private String company;
	@Positive
	private double price;
	@NotBlank
	private String description;
	@Positive
	private int validity;
	@JsonIgnore
	private List<String> boughtByUsersList;

	public Coupon(@NotBlank String couponCode, @NotNull String company, @NotNull double price,
			@NotBlank String description, @NotNull int validity, List<String> boughtByUsersList) {
		super();
		this.couponCode = couponCode;
		this.company = company;
		this.price = price;
		this.description = description;
		this.validity = validity;
		this.boughtByUsersList = boughtByUsersList;
	}

	public Coupon(@NotBlank String couponCode, @NotNull String company, @NotNull double price,
			@NotBlank String description, @NotNull int validity) {
		super();
		this.couponCode = couponCode;
		this.company = company;
		this.price = price;
		this.description = description;
		this.validity = validity;
	}
		
}
