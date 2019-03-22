package com.stackroute.userservice.RepositoryTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.Model.User;
import com.stackroute.userservice.Repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest 
{
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@Before
	public void setup()
	{
		user = new User();
		user.setUserId("John");
		user.setFirstName("Johnny");
		user.setLastName("Bairstow");
		user.setPassword("123456");
	}
	
	@Test
	public void testRegisterUserSuccess()
	{
		userRepository.save(user);
		assertEquals(true,userRepository.existsById(user.getUserId()));
	}
}
