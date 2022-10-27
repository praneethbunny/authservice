package com.tweetapp.userauthorizationservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.userauthorizationservice.dao.ResponseDAO;
import com.tweetapp.userauthorizationservice.dao.UserDAO;
import com.tweetapp.userauthorizationservice.exception.UserAcountException;
import com.tweetapp.userauthorizationservice.model.User;
import com.tweetapp.userauthorizationservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDAO registerNewUser(User user) throws UserAcountException {
		log.info("inside register new user");
		Optional<User> option = repo.findById(user.getUserName());
		Optional<User> option2 = repo.findByEmailId(user.getEmailId());
		UserDAO userResult;
		if(option.isPresent() || option2.isPresent()) {
			log.error(user.getUserName() + " already exists");
			throw new UserAcountException(user.getUserName() + " already exists");
		}else {
			user.setActive(true);
			user.setRole("user");
			try {
			User temp = repo.save(user);
			userResult = new UserDAO(temp);
			}catch(Exception ex) {
				log.error("couldn't register");
				throw new UserAcountException("couldn't register");
			}
		}
		return userResult;
		
	}

	@Override	
	public UserDAO forgotPassword(User user) throws UserAcountException{
		log.info("inside forgot password");
		Optional<User> option = repo.findById(user.getUserName());
		UserDAO userResult;
		if(option.isPresent()) {
			User temp = option.get();
			temp.setPassword(user.getPassword());
			try {
			User temporary = repo.save(temp);
			userResult = new UserDAO(temporary);
			}catch(Exception ex) {
				log.error("couldn't register");
				throw new UserAcountException("couldn't register");
			}
		}else {
			log.error(user.getUserName() + " doesn't exist");
			throw new UserAcountException(user.getUserName() + " doesn't exist");
		}
		return userResult;
	}

	@Override	
	public UserDAO editUser(User user) throws UserAcountException{
		log.info("inside edit user");
		Optional<User> option = repo.findById(user.getUserName());
		
		UserDAO userResult;
		if(option.isPresent()) {
			User temp = option.get();
			temp.setFirstName(user.getFirstName());
			temp.setLastName(user.getLastName());
			temp.setEmailId(user.getEmailId());
			temp.setContactNo(user.getContactNo());
			try {
			User temporary = repo.save(temp);
			userResult = new UserDAO(temporary);
			}catch(Exception ex) {
				log.error("couldn't register");
				throw new UserAcountException("couldn't register");
			}
		}else {
			log.error(user.getUserName() + " doesn't exist");
			throw new UserAcountException(user.getUserName() + " doesn't exist");
		}
		return userResult;
	}

	@Override	
	public List<UserDAO> getAllUsers() throws UserAcountException{
		List<UserDAO> result = new ArrayList<>();
		try {
			log.info("inside get all users");
			for(User user: repo.findAll()) {
				result.add(new UserDAO(user));
			}if(result.isEmpty()) {
				log.error("generic Exception or db is empty");
				throw new UserAcountException("generic Exception or db is empty");
			}
		}catch(Exception ex) {
			log.error("couldn't connect with db");
			throw new UserAcountException("couldn't connect with db");
		}
		return result;
	}

	@Override	
	public UserDAO getUserByUserName(String userName) throws UserAcountException {
		log.info("inside get user by username");
		Optional<User> option = repo.findById(userName);
		UserDAO userResult;
		if(option.isPresent()) {
			userResult = new UserDAO(option.get());
			}else {
				log.error(userName + " doesn't exist");
			throw new UserAcountException(userName + " doesn't exist");
		}
		return userResult;
	}

	@Override	
	public ResponseDAO generateResponse(String username, String token) {
		log.info("inside generate Response");
		ResponseDAO response = new ResponseDAO();
		response.setServerCurrentTime(new Date(System.currentTimeMillis()));
		response.setTokenExpirationTime(new Date(System.currentTimeMillis() + 60*60*1000));
		response.setRole("user");
		response.setJwtAuthToken(token);
		response.setUsername(username);
		return response;
	}
}
