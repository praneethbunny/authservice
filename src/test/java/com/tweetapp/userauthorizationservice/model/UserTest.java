package com.tweetapp.userauthorizationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	
	@Mock
	private User user;
	
private AutoCloseable closable;
	
	@BeforeEach
	public void setup() {
		closable = MockitoAnnotations.openMocks(this);
		user = new User();
		user.setContactNo("1234567890");
		user.setActive(true);
		user.setEmailId("test@mail.com");
		user.setFirstName("test");
		user.setLastName("test");
		user.setPassword("pass");
		user.setRole("test");
		user.setUserName("username");
	}
	

	@AfterEach
	void closeService() throws Exception {
		closable.close();
	}
	
	
	@Test
	public void userTestGetters() {
		assertEquals("1234567890",user.getContactNo());
		assertEquals("test@mail.com",user.getEmailId());
		assertEquals("test",user.getFirstName());
		assertEquals("test",user.getLastName());
		assertEquals("pass",user.getPassword());
		assertEquals("test",user.getRole());
		assertEquals("username",user.getUserName());
	}
	
	@Test
	public void gettersMyUserDetailsTest() {
		MyUserDetails myd=new MyUserDetails(user);
		assertEquals("pass",myd.getPassword());
		assertEquals("username",myd.getUsername());
		assertEquals(true,myd.isAccountNonExpired());
		assertEquals(true,myd.isAccountNonLocked());
		assertEquals(true,myd.isCredentialsNonExpired());
		assertEquals(true,myd.isEnabled());
	}

}
