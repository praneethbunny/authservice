package com.tweetapp.userauthorizationservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

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
import org.springframework.security.core.userdetails.UserDetails;

import com.tweetapp.userauthorizationservice.config.JwtTokenUtil;
import com.tweetapp.userauthorizationservice.dao.ResponseDAO;
import com.tweetapp.userauthorizationservice.model.Request;
import com.tweetapp.userauthorizationservice.service.JwtUserDetailsService;
import com.tweetapp.userauthorizationservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class JwtAuthenticationControllerTests {
	@Spy
	@InjectMocks
	JwtAuthenticationController jwtAuthenticationController;
	
	@Mock
	private JwtUserDetailsService jwtService;
	
	@Mock
	private JwtTokenUtil util;
	
	@Mock
	private UserDetails userDetailsService;
	
	private AutoCloseable closable;
	
	@BeforeEach
	public void setup() {
		closable = MockitoAnnotations.openMocks(this);
		log.info("start");
		ResponseDAO responseDao=new ResponseDAO();
		responseDao.setJwtAuthToken("token");
		responseDao.setRole("user");
		responseDao.setUsername("admin");
		responseDao.setServerCurrentTime(new Date(System.currentTimeMillis()));
		responseDao.setTokenExpirationTime(new Date(System.currentTimeMillis() + 60*60*1000));
		log.info("end");
	}
	
	@AfterEach
	void closeService() throws Exception {
		closable.close();
	}
	
	
	@Test
	public void testCreateAuthenticationToken() throws Exception {
		assertThrows(Exception.class,()->{
		Request request = new Request();
		request.setUsername("admin");
		request.setPassword("password");
		jwtAuthenticationController.createAuthenticationToken(request);
		});
	}
	
	@Test
	public void testHealthCheck() {
		ResponseEntity<?> response = jwtAuthenticationController.healthCheck();
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals("auth-Ok",response.getBody());
	}
	
}
