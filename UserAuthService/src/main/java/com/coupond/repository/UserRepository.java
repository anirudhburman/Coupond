package com.coupond.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.coupond.entity.User;

public interface UserRepository extends MongoRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	
	void deleteByUsername(String username);
}
