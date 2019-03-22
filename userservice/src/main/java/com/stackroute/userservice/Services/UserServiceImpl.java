package com.stackroute.userservice.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.Exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.Exceptions.UserNotFoundException;
import com.stackroute.userservice.Model.User;
import com.stackroute.userservice.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException
	{
		Optional<User> fetchedUser = userRepository.findById(user.getUserId());
		if(fetchedUser.isPresent())
			throw new UserAlreadyExistsException("User already exists!");
		userRepository.save(user);
		return true;
	}
	
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException
	{
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if(user == null)
			throw new UserNotFoundException("Wrong credentials!");
		return user;
	}
}
