package com.stackroute.userservice.ServiceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.Exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.Exceptions.UserNotFoundException;
import com.stackroute.userservice.Model.User;
import com.stackroute.userservice.Repository.UserRepository;
import com.stackroute.userservice.Services.UserServiceImpl;

public class UserServiceTest 
{
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	private User user;
	
	Optional<User> options;
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setUserId("John");
		user.setFirstName("Johnny");
		user.setLastName("Bairstow");
		user.setPassword("123456");
		
		options = Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistsException
	{
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals(true,flag);
		verify(userRepository, times(1)).save(user);
	}
	
	@Test(expected=UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserAlreadyExistsException
	{
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals(true,flag);
		verify(userRepository, times(0)).save(user);
	}
	
	@Test
	public void testValidateSuccess() throws UserNotFoundException
	{
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User fetchedUser = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertEquals("John", fetchedUser.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
	
	@Test(expected=UserNotFoundException.class)
	public void testValidateUserFailure() throws UserNotFoundException
	{
		when(userRepository.findById("John")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
}
