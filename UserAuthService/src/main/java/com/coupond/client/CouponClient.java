package com.coupond.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.coupond.dto.CouponDto;
import com.coupond.errorDecoder.CustomErrorDecoder;

@FeignClient(url="http://localhost:9091/coupon", name="coupon-service", configuration = CustomErrorDecoder.class)
public interface CouponClient {

	@PostMapping("/addCoupon")
	public ResponseEntity<CouponDto> addCoupon(@RequestBody CouponDto coupon);
	
	@DeleteMapping("/deleteCouponByCode/{code}")
	public ResponseEntity<String> deleteCouponByCode(@PathVariable("code") String couponCode);
	
	@DeleteMapping("/deleteCouponById/{id}")
	public ResponseEntity<String> deleteCouponById(@PathVariable Integer id);
	
	@PutMapping("/updateCoupon")
	public ResponseEntity<CouponDto> updateCoupon(@RequestBody CouponDto coupon);
	
	@GetMapping("/getCouponById/{id}")
	public ResponseEntity<CouponDto> getCouponById(@PathVariable Integer id);
	
	@GetMapping("/getCouponByCode/{code}")
	public ResponseEntity<CouponDto> getCouponByCouponCode(@PathVariable("code") String couponCode);
	
	@GetMapping("/getAllCoupons")
	public ResponseEntity<List<CouponDto>> getAllCoupons();
}
