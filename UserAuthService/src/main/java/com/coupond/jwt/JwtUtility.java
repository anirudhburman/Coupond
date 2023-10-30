package com.coupond.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.coupond.service.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtility {

	@Value("${jwtSecret}")
	private String jwtSecret = "jwtSecretKey";

	@Value("${jwtExpirationMs}")
	private long jwtExpirationMs;

	public String generateToken(Authentication authentication) {

//		UserDetailsImpl userPrincipal=(UserDetailsImpl) authentication.getPrincipal();
//		List<String> roles=userPrincipal.getAuthorities()
//				.stream()
//				.map(GrantedAuthority::getAuthority)
//				.collect(Collectors.toList());
//		Date now=new Date();
//		Date expireDate=new Date(now.getTime()+jwtExpirationMs);
//		return Jwts.builder()
//				.setSubject(userPrincipal.getUsername())
//				.claim("roles", roles)
//				.setIssuedAt(now)
//				.setExpiration(expireDate)
//				.signWith(SignatureAlgorithm.HS512, jwtSecret)
//				.compact();

		UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		Date now = new Date();
		Date Expiredate = new Date(now.getTime() + jwtExpirationMs);
		return Jwts.builder().setSubject(user.getUsername()).claim("roles", roles).setIssuedAt(now)
				.setExpiration(Expiredate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Boolean validateToken(String token) {
//	   try {
//		   Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//		   return true;
//	   }catch(Exception e) {
//		   return false;
//	   }

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

//public String getUsername(String token) {
//	return parseClaims(token).getSubject();
//}

	public String getUsername(String token) {
		return parseClaims(token).getSubject();
	}
//public List<String> extractRolesFromToken(String token){
//	Claims claims=parseClaims(token);
//	return claims.get("roles",List.class);
//}

	public List<String> extractRolesFromToken(String token) {
		Claims claims = parseClaims(token);
		return claims.get("roles", List.class);
	}

	private Claims parseClaims(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims;
	}

}
