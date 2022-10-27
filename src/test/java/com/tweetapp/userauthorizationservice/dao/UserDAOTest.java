package com.tweetapp.userauthorizationservice.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.tweetapp.userauthorizationservice.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

	@Mock
	private UserDAO dao;
	
	private AutoCloseable closable;
	
	@BeforeEach
	public void setup() {
		closable = MockitoAnnotations.openMocks(this);
		dao = new UserDAO();
		dao.setContactNo("1234567890");
		dao.setEmailId("test@mail.com");
		dao.setFirstName("test");
		dao.setLastName("test");
		dao.setUserName("tester");
	}

	@AfterEach
	void closeService() throws Exception {
		closable.close();
	}
	
	@Test
	public void userDAOTest() {
		assertEquals("1234567890",dao.getContactNo());
		assertEquals("test@mail.com",dao.getEmailId());
		assertEquals("test",dao.getFirstName());
		assertEquals("test",dao.getLastName());
		assertEquals("tester",dao.getUserName());
	}
}
