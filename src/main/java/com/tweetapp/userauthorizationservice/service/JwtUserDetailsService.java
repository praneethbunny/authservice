package com.tweetapp.userauthorizationservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.userauthorizationservice.model.MyUserDetails;
import com.tweetapp.userauthorizationservice.model.User;
import com.tweetapp.userauthorizationservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername");
		log.info("loadUserByUsername"+username);

		Optional<User> optionaluserList = repo.findById(username);
		if(optionaluserList.isPresent()) {
			
			User user=optionaluserList.get();
			System.out.println(user);
			return new MyUserDetails(user);
		}else {
			log.error("User not found with username: " + username);
			throw new UsernameNotFoundException("User not found with username: " + username);

		}
		

	}


}