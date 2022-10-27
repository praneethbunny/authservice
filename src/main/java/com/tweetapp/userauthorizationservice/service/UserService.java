package com.tweetapp.userauthorizationservice.service;

import java.util.List;

import com.tweetapp.userauthorizationservice.dao.ResponseDAO;
import com.tweetapp.userauthorizationservice.dao.UserDAO;
import com.tweetapp.userauthorizationservice.exception.UserAcountException;
import com.tweetapp.userauthorizationservice.model.User;

public interface UserService {

	public UserDAO registerNewUser(User user) throws UserAcountException;
	
	public UserDAO forgotPassword(User user) throws UserAcountException;
	
	public UserDAO editUser(User user) throws UserAcountException;
	
	public List<UserDAO> getAllUsers() throws UserAcountException;
	
	public UserDAO getUserByUserName(String userName) throws UserAcountException;
	
	public ResponseDAO generateResponse(String username, String token);
}
