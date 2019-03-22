package com.stackroute.userservice.Services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator 
{
	public static Logger logger = LoggerFactory.getLogger(JwtSecurityTokenGeneratorImpl.class);
	
	@Override
	public Map<String, String> generateToken(User user)
	{
		logger.info("Inside generateToken");
		String jwtToken = "";
		
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256,"secret").compact();
		
		Map<String, String> map = new HashMap<>();
		map.put("token",jwtToken);
		map.put("message", "User successfully logged in");
		return map;
	}
}
