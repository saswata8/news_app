package com.stackroute.userservice.Services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.userservice.Model.User;


public interface SecurityTokenGenerator 
{
	Map<String, String> generateToken(User user); 
}
