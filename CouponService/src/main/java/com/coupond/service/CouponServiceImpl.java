package com.coupond.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coupond.enitity.Coupon;
import com.coupond.exception.CouponAlreadyExistsException;
import com.coupond.exception.ResourceNotFoundException;
import com.coupond.repository.CouponRepository;
import com.coupond.repository.SequenceRepository;
import com.coupond.util.SequenceGeneratorService;

@Service
public class CouponServiceImpl implements ICouponService {
	
	@Autowired
	CouponRepository couponRepo;
	
	@Autowired
	SequenceRepository seqRepo;
	
	@Autowired
	SequenceGeneratorService seqGen;

	@Override
	public Coupon addCoupon(Coupon coupon) throws CouponAlreadyExistsException {
		if(couponRepo.findByCouponCode(coupon.getCouponCode()).isPresent()) {
			throw new CouponAlreadyExistsException();
		}
		coupon.setCouponId(seqGen.generateSequence(coupon.SEQUENCE_NAME));	
		return couponRepo.save(coupon);
	}

	@Override
	public Coupon getCouponById(Integer id) throws ResourceNotFoundException {
		return couponRepo.findById(id).orElseThrow(ResourceNotFoundException :: new);
	}

	@Override
	public List<Coupon> getAllCoupons() {
		return couponRepo.findAll();
	}

	@Override
	public String deleteCouponByCouponCode(String couponCode) throws ResourceNotFoundException {
		
		Coupon coupon = couponRepo.findByCouponCode(couponCode).orElseThrow(ResourceNotFoundException::new);
		couponRepo.delete(coupon);
		return "Coupon deleted by code successfully!";
	}

	@Override
	public Coupon updateCoupon(Coupon coupon) throws ResourceNotFoundException {
		Coupon coup = couponRepo.findById(coupon.getCouponId()).orElseThrow(ResourceNotFoundException::new);
		return couponRepo.save(coupon);
	}

	@Override
	public Coupon getCouponByCouponCode(String couponCode) throws ResourceNotFoundException {
		return couponRepo.findByCouponCode(couponCode).orElseThrow(ResourceNotFoundException :: new);
	}

	@Override
	public String deleteCouponById(Integer id) throws ResourceNotFoundException {
		Coupon coupon = couponRepo.findById(id).orElseThrow(ResourceNotFoundException :: new);
		couponRepo.delete(coupon);
		return "Coupon deleted by ID successfully!";
	}

}
