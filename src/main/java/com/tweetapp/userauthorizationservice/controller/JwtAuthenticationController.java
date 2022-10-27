package com.tweetapp.userauthorizationservice.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.userauthorizationservice.config.JwtTokenUtil;
import com.tweetapp.userauthorizationservice.dao.ResponseDAO;
import com.tweetapp.userauthorizationservice.model.Request;
import com.tweetapp.userauthorizationservice.service.UserService;

import lombok.extern.slf4j.Slf4j;



@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/tweets")
@Slf4j
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Request request)
			throws Exception {
		log.info("Inside login controller");
		authenticate(request.getUsername(), request.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(request.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		ResponseDAO response = userService.generateResponse(request.getUsername(),token);
		log.info("Outside login controller");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println(e);
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("auth-Ok", HttpStatus.OK);
	}
	
}