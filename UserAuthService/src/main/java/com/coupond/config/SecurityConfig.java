package com.coupond.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.coupond.jwt.EntryPointJwt;
import com.coupond.jwt.TokenFilter;
import com.coupond.service.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	EntryPointJwt entryPoint;

	@Autowired
	TokenFilter tokenFilter;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Bean
	public DaoAuthenticationProvider authenticate() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public SecurityFilterChain doFilter(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(entryPoint)
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/actuator/**", "/error", "/app/**", "/coupon/getAllCoupons", "/user/registerUser", "/v3/**", "/swagger-ui/**").permitAll()
				.requestMatchers("/user/**", "/coupon/**").authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

