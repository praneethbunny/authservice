package com.tweetapp.userauthorizationservice.dao;

import java.util.Date;

public class ResponseDAO {

	public String username;
	public String jwtAuthToken;
	public Date serverCurrentTime;
	public Date tokenExpirationTime;
	public String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJwtAuthToken() {
		return jwtAuthToken;
	}
	public void setJwtAuthToken(String jwtAuthToken) {
		this.jwtAuthToken = jwtAuthToken;
	}
	public Date getServerCurrentTime() {
		return serverCurrentTime;
	}
	public void setServerCurrentTime(Date serverCurrentTime) {
		this.serverCurrentTime = serverCurrentTime;
	}
	public Date getTokenExpirationTime() {
		return tokenExpirationTime;
	}
	public void setTokenExpirationTime(Date tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
