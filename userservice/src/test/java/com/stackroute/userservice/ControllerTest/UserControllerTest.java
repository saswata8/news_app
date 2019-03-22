package com.stackroute.userservice.ControllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.Controller.UserController;
import com.stackroute.userservice.Model.User;
import com.stackroute.userservice.Services.SecurityTokenGenerator;
import com.stackroute.userservice.Services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest 
{
		@Autowired
		private MockMvc MockMvc;

		@MockBean
		private UserService userService;
		
		@MockBean
		private SecurityTokenGenerator tokenGenerator;

		private User user;

		private ObjectMapper objectMapper;

		@Before
		public void setup()
		{
			objectMapper = new ObjectMapper();
			user = new User();
			user.setUserId("John");
			user.setFirstName("Johnny");
			user.setLastName("Bairstow");
			user.setPassword("123456");
		}
		
		@Test
		public void testRegisterUser() throws Exception
		{
			when(userService.saveUser(user)).thenReturn(true);
			MockMvc.perform(post("/user/register").content(objectMapper.writeValueAsString(user))
					.contentType("application/json;charset=UTF-8")).andExpect(status().isCreated());
		}
		
		@Test
		public void testLoginUser() throws Exception
		{
			when(userService.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
			MockMvc.perform(post("/user/login").content(objectMapper.writeValueAsString(user))
					.contentType("application/json;charset=UTF-8")).andExpect(status().isOk());
		}
}
