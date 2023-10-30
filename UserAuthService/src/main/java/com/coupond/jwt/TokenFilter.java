package com.coupond.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.coupond.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenFilter extends OncePerRequestFilter {
	
  @Autowired
  private JwtUtility jwtUtils;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

 
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
    	String token=null;
    	String header=request.getHeader("Authorization");
    	if(header!=null && header.startsWith("Bearer")) {
    		token=header.substring(7);
    	}
    	if(token!=null && jwtUtils.validateToken(token)) {
    		jwtUtils=new JwtUtility();
    		String username=jwtUtils.getUsername(token);
    		UserDetails userDetails=userDetailsService.loadUserByUsername(username);
    		UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(
    				username,null,userDetails.getAuthorities());
    		SecurityContextHolder.getContext().setAuthentication(authentication);
    	}
    }
    catch(Exception e) {
    	System.out.println(e.getClass()+"    "+e.getMessage());
    }
    filterChain.doFilter(request, response);
  }

  
}
