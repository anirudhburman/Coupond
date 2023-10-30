package com.coupond.service;

import java.util.List;

import com.coupond.enitity.Coupon;
import com.coupond.exception.CouponAlreadyExistsException;
import com.coupond.exception.ResourceNotFoundException;

public interface ICouponService {
	
	Coupon addCoupon(Coupon coupon) throws CouponAlreadyExistsException;
	
	String deleteCouponByCouponCode(String couponCode) throws ResourceNotFoundException;
	
	String deleteCouponById(Integer id) throws ResourceNotFoundException;
	
	Coupon updateCoupon(Coupon coupon) throws ResourceNotFoundException;

	Coupon getCouponById(Integer id) throws ResourceNotFoundException;
	
	Coupon getCouponByCouponCode(String couponCode) throws ResourceNotFoundException;
	
	List<Coupon> getAllCoupons();
}
