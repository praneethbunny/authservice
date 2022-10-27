package com.tweetapp.userauthorizationservice.dao;

import com.tweetapp.userauthorizationservice.model.User;

public class UserDAO {
	
	private String userName;
	
	private String firstName;
	
	private String lastName;

	private String emailId;
	
	private String contactNo;
	
	public UserDAO(User user) {
		this.userName = user.getUserName();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.emailId = user.getEmailId();
		this.contactNo = user.getContactNo();
	}
	
	public UserDAO() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	
}
