package com.stackroute.userservice.Services;

import com.stackroute.userservice.Exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.Exceptions.UserNotFoundException;
import com.stackroute.userservice.Model.User;

public interface UserService 
{
	boolean saveUser(User user) throws UserAlreadyExistsException; 
	
	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException; 
}
