package com.coupond.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
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

import com.coupond.client.CouponClient;
import com.coupond.dto.CouponDto;
import com.coupond.exception.ResourceAlreadyExistsException;
import com.coupond.exception.ResourceNotFoundException;
import com.coupond.exception.UserNotFoundException;
import com.coupond.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/coupon")
@EnableFeignClients(basePackages = "com.coupond.client")
@Slf4j
public class CouponClientController {

	@Autowired
	CouponClient couponClient;

	@Autowired
	UserService userService;

	@PostMapping("/addCoupon")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<CouponDto> addCoupon(@Valid @RequestBody CouponDto coupon)
			throws ResourceAlreadyExistsException, UserNotFoundException {
		log.info("Saving.. " + coupon);
		CouponDto c = couponClient.addCoupon(coupon).getBody();
		CouponDto cdto = new CouponDto(c.getCouponId(), c.getCouponCode(), c.getCompany(), c.getPrice(),
				c.getDescription(), c.getValidity());
		return new ResponseEntity<>(cdto, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteCouponByCode/{code}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deleteCouponByCode(@Valid @PathVariable("code") String couponCode)
			throws ResourceNotFoundException {
		log.info("Deleting coupon with code " + couponCode);
		return new ResponseEntity<>(couponClient.deleteCouponByCode(couponCode).getBody(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteCouponById/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deleteCouponById(@Valid @PathVariable Integer id) throws ResourceNotFoundException {
		log.info("Deleting coupon with Id " + id);
		return new ResponseEntity<>(couponClient.deleteCouponById(id).getBody(), HttpStatus.OK);
	}

	@PutMapping("/updateCoupon")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<CouponDto> updateCoupon(@Valid @RequestBody CouponDto coupon)
			throws ResourceNotFoundException, UserNotFoundException {
		log.info("Updating Coupon with Id " + coupon.getCouponId());
		return new ResponseEntity<>(couponClient.updateCoupon(coupon).getBody(), HttpStatus.OK);
	}

	@GetMapping("/getCouponById/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<CouponDto> getCouponById(@Valid @PathVariable Integer id)
			throws ResourceNotFoundException, UserNotFoundException {
		log.info(couponClient.getCouponById(id).toString());
		CouponDto cdto = couponClient.getCouponById(id).getBody();
		return new ResponseEntity<>(cdto, HttpStatus.OK);
	}

	@GetMapping("/getCouponByCode/{code}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<CouponDto> getCouponByCouponCode(@Valid @PathVariable("code") String couponCode)
			throws ResourceNotFoundException, UserNotFoundException {
		log.info(couponClient.getCouponByCouponCode(couponCode).toString());
		CouponDto cdto = couponClient.getCouponByCouponCode(couponCode).getBody();
		return new ResponseEntity<>(cdto, HttpStatus.OK);
	}

	@GetMapping("/getAllCoupons")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<List<CouponDto>> getAllCoupons() throws UserNotFoundException {
		log.info(couponClient.getAllCoupons().toString());
		List<CouponDto> listToReturn = couponClient.getAllCoupons().getBody();
		return new ResponseEntity<>(listToReturn, HttpStatus.OK);
	}
}
