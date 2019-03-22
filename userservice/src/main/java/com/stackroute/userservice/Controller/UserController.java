package com.stackroute.userservice.Controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.Model.User;
import com.stackroute.userservice.Services.SecurityTokenGenerator;
import com.stackroute.userservice.Services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityTokenGenerator tokenGenerator;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		try
		{
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered successfully!",HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user)
	{
		try
		{
			String userId = user.getUserId();
			String password = user.getPassword();
			
			if(userId == null || password == null)
				throw new Exception("UserId or password can't be empty");
			
			User fetchedUser = userService.findByUserIdAndPassword(userId, password);
			
//			if(fetchedUser == null)
//				throw new Exception("User with given id doesn't exist");
			
			String pwd = fetchedUser.getPassword();
			if(!pwd.equals(password))
				throw new Exception("Invalid login credentials, please check userid and password");
			
			Map<String, String> map = tokenGenerator.generateToken(user); 
			return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
	}
	
	
}
