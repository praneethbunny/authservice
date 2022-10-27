package com.tweetapp.userauthorizationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.tweetapp.userauthorizationservice.controller.UserControllerTest;
import com.tweetapp.userauthorizationservice.dao.ResponseDAO;
import com.tweetapp.userauthorizationservice.dao.UserDAO;
import com.tweetapp.userauthorizationservice.exception.UserAcountException;
import com.tweetapp.userauthorizationservice.model.User;
import com.tweetapp.userauthorizationservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	private UserDAO userDAO;
	
	@Mock
	private User user;
	
	@Mock
	private UserRepository repo;
	
	@Spy
	@InjectMocks
	private UserServiceImpl userServiceimpl;
	
	private AutoCloseable closable;
	
	@BeforeEach
	public void setup() {
		closable = MockitoAnnotations.openMocks(this);
		user = new User();
		user.setActive(true);
		user.setContactNo("9876543210");
		user.setEmailId("admin@email.com");
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setPassword("pass");
		user.setRole("user");
		user.setUserName("admin");
		userDAO=new UserDAO(user);user = new User();
		user.setActive(true);
		user.setContactNo("9876543210");
		user.setEmailId("admin@email.com");
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setPassword("pass");
		user.setRole("user");
		user.setUserName("admin");
		userDAO=new UserDAO(user);
	}
	
	@AfterEach
	void closeService() throws Exception {
		closable.close();
	}

	@Test
	public void testRegisterNewUserException() {
		assertThrows(Exception.class,()->{
			userServiceimpl.registerNewUser(user);
		});
	}
	
	@Test
	public void testeditUserException() {
		assertThrows(Exception.class,()->{
			userServiceimpl.editUser(user);
		});
	}
	
	@Test
	public void testForgotPasswordrException() {
		assertThrows(Exception.class,()->{
			userServiceimpl.forgotPassword(user);
		});
	}
	
	@Test
	public void testgetAllUsersException() {
		assertThrows(Exception.class,()->{
			userServiceimpl.getAllUsers();
		});
	}
	
	@Test
	public void testGetUserByIdException() {
		assertThrows(Exception.class,()->{
			userServiceimpl.getUserByUserName("test");
		});
	}
	
	@Test
	public void testGenerateresponse() {
		ResponseDAO response = new ResponseDAO();
		response.setServerCurrentTime(new Date(System.currentTimeMillis()));
		response.setTokenExpirationTime(new Date(System.currentTimeMillis() + 60*60*1000));
		response.setRole("user");
		response.setJwtAuthToken("token");
		response.setUsername("test");
		ResponseDAO result  = userServiceimpl.generateResponse("test", "token");
		assertNotNull(result);
	}
	
	@Test
	public void testRegisterUser() throws UserAcountException {
		when(repo.save(user)).thenReturn(user);
		UserDAO dao = userServiceimpl.registerNewUser(user);
		assertNotNull(dao);
	}
	
//	@Test
//	public void testGetAllUsers() throws UserAcountException {
//		when(repo.findAll()).thenReturn((Iterable<User>) user);
//		List<UserDAO> dao = userServiceimpl.getAllUsers();
//		assertNotNull(dao);
//	}
	
}
