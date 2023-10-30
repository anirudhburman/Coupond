package com.coupond.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupond.entity.User;
import com.coupond.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userRepository.findByUsername(username);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("User Not Found with username: "+username);
		}
		return UserDetailsImpl.getUser(user.get());
	}

}
