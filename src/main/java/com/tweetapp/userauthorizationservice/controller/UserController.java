package com.tweetapp.userauthorizationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.userauthorizationservice.dao.UserDAO;
import com.tweetapp.userauthorizationservice.exception.UserAcountException;
import com.tweetapp.userauthorizationservice.model.User;
import com.tweetapp.userauthorizationservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/tweets")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public ResponseEntity<?> registerNewUser(@RequestBody User user) throws UserAcountException{
		log.info("Inside register controller");
		UserDAO response = userService.registerNewUser(user);
		return new ResponseEntity<>(response  , HttpStatus.OK);
	}
	
	@RequestMapping(value="/{username}/forgot" , method=RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@PathVariable String username,@RequestBody User user) throws UserAcountException{
		log.info("Inside forgot password controller");
		UserDAO response = userService.forgotPassword(user);
		return new ResponseEntity<>(response  , HttpStatus.OK);
	}
	
	@RequestMapping(value="/{username}/edit" , method=RequestMethod.POST)
	public ResponseEntity<?> editUser(@PathVariable String username,@RequestBody User user) throws UserAcountException{
		log.info("Inside edit profile controller");
		UserDAO response = userService.editUser(user);
		return new ResponseEntity<>(response  , HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/all" , method=RequestMethod.GET)
	public ResponseEntity<?> getAllUsers() throws UserAcountException{
		log.info("Inside get all users controller");
		List<UserDAO> response = userService.getAllUsers();
		return new ResponseEntity<>(response  , HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/search/{username}" , method=RequestMethod.GET)
	public ResponseEntity<?> getUserByUserName(@PathVariable String username) throws UserAcountException{
		log.info("Inside get users by id controller");
		UserDAO response = userService.getUserByUserName(username);
		return new ResponseEntity<>(response  , HttpStatus.OK);
	}
}
