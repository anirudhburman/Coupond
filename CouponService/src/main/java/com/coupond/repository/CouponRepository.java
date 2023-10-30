package com.coupond.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.coupond.enitity.Coupon;

@Repository
public interface CouponRepository extends MongoRepository<Coupon, Integer> {

	Optional<Coupon> findByCouponCode(String couponCode);
}
