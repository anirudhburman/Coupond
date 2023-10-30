package com.coupond.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupond.enitity.Coupon;
import com.coupond.exception.CouponAlreadyExistsException;
import com.coupond.exception.ResourceNotFoundException;
import com.coupond.service.ICouponService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/coupon")
public class CouponController {
	
	@Autowired
	ICouponService couponService;

	@PostMapping("/addCoupon")
	public ResponseEntity<Coupon> addCoupon(@Valid @RequestBody Coupon coupon) throws CouponAlreadyExistsException {
		log.info("Saving.. " + coupon);
		return new ResponseEntity<>(couponService.addCoupon(coupon), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteCouponByCode/{code}")
	public ResponseEntity<String> deleteCouponByCode(@Valid @PathVariable("code") String couponCode) throws ResourceNotFoundException {
		log.info("Deleting coupon with code " + couponCode);
		return new ResponseEntity<>(couponService.deleteCouponByCouponCode(couponCode), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCouponById/{id}")
	public ResponseEntity<String> deleteCouponById(@Valid @PathVariable Integer id) throws ResourceNotFoundException {
		log.info("Deleting coupon with Id " + id);
		return new ResponseEntity<>(couponService.deleteCouponById(id), HttpStatus.OK);
	}
	
	@PutMapping("/updateCoupon")
	public ResponseEntity<Coupon> updateCoupon(@Valid @RequestBody Coupon coupon) throws ResourceNotFoundException {
		log.info("Updating Coupon with Id " + coupon.getCouponId());
		return new ResponseEntity<>(couponService.updateCoupon(coupon), HttpStatus.OK);
	}
	
	@GetMapping("/getCouponById/{id}")
	public ResponseEntity<Coupon> getCouponById(@Valid @PathVariable Integer id) throws ResourceNotFoundException {
		log.info(couponService.getCouponById(id).toString());
		return new ResponseEntity<>(couponService.getCouponById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getCouponByCode/{code}")
	public ResponseEntity<Coupon> getCouponByCouponCode(@Valid @PathVariable("code") String couponCode) throws ResourceNotFoundException {
		log.info(couponService.getCouponByCouponCode(couponCode).toString());
		return new ResponseEntity<>(couponService.getCouponByCouponCode(couponCode), HttpStatus.OK);
	}
	
	@GetMapping("/getAllCoupons")
	public ResponseEntity<List<Coupon>> getAllCoupons() {
		log.info(couponService.getAllCoupons().toString());
		return new ResponseEntity<>(couponService.getAllCoupons(), HttpStatus.OK);
	}
	
}
