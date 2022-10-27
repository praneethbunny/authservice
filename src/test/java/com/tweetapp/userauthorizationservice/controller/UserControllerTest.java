package com.tweetapp.userauthorizationservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tweetapp.userauthorizationservice.dao.UserDAO;
import com.tweetapp.userauthorizationservice.exception.UserAcountException;
import com.tweetapp.userauthorizationservice.model.User;
import com.tweetapp.userauthorizationservice.service.UserService;
import com.tweetapp.userauthorizationservice.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Spy
	@InjectMocks
	UserController userController;
	
	@Mock
	private UserDAO userDAO;
	
	@Mock
	private User user;
	
	@Mock
	private UserServiceImpl userService;
	
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
	public void testRegisterNewUser() throws UserAcountException {
		when(userService.registerNewUser(user)).thenReturn(userDAO);
		ResponseEntity<?> response = userController.registerNewUser(user);
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(userDAO,response.getBody());
	}
	
	@Test
	public void testRegisterNewUserThrowsException() throws UserAcountException {
		assertThrows(UserAcountException.class, ()->{
		when(userService.registerNewUser(user)).thenThrow(UserAcountException.class);
		ResponseEntity<?> response = userController.registerNewUser(user);
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		});
	}
	
	@Test
	public void testForgetPassword() throws UserAcountException {
		when(userService.forgotPassword(user)).thenReturn(userDAO);
		ResponseEntity<?> response = userController.forgotPassword("admin", user);
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(userDAO,response.getBody());
	}
	
	@Test
	public void testForgetPasswordThrow() throws UserAcountException {
		assertThrows(UserAcountException.class, ()->{
		when(userService.forgotPassword(user)).thenThrow(UserAcountException.class);
		ResponseEntity<?> response = userController.forgotPassword("admin", user);
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		});
	}
	
	@Test
	public void testEditUser() throws UserAcountException {
		when(userService.editUser(user)).thenReturn(userDAO);
		ResponseEntity<?> response = userController.editUser("admin", user);
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(userDAO,response.getBody());
	}
	
	@Test
	public void testEditUserException(){
		assertThrows(UserAcountException.class, ()->{
			when(userService.editUser(user)).thenThrow(UserAcountException.class);
			ResponseEntity<?> response = userController.editUser("admin", user);
			assertNotNull(response.getStatusCode());
			assertEquals(HttpStatus.OK,response.getStatusCode());
			});
	}
	
	@Test
	public void testGetAllUser() throws UserAcountException {
		List<UserDAO> result= new ArrayList<>();
		result.add(userDAO);
		when(userService.getAllUsers()).thenReturn(result);
		ResponseEntity<?> response = userController.getAllUsers();
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(result,response.getBody());
		
	}
	
	@Test
	public void testGetUserByUserName() throws UserAcountException {
		when(userService.getUserByUserName("admin")).thenReturn(userDAO);
		ResponseEntity<?> response = userController.getUserByUserName("admin");
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(userDAO,response.getBody());
	}
	
	
}
