package com.tweetapp.userauthorizationservice.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.userauthorizationservice.model.User;

@EnableScan
@Repository
public interface UserRepository extends CrudRepository<User , String> {

 Optional<User> findByEmailId(String emailId);
}
