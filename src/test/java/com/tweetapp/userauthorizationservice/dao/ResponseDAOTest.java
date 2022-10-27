package com.tweetapp.userauthorizationservice.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
public class ResponseDAOTest {

	@Mock
	private ResponseDAO dao;
	
	private AutoCloseable closable;
	
	@BeforeEach
	void setup() {
		closable = MockitoAnnotations.openMocks(this);
		dao = new ResponseDAO();
		dao.setJwtAuthToken("token");
		dao.setRole("test");
		dao.setServerCurrentTime(null);
		dao.setTokenExpirationTime(null);
		dao.setUsername("test");
	}
	
	

	@AfterEach
	void closeService() throws Exception {
		closable.close();
	}
	
	@Test
	public void getterTers() {
		assertNotNull(dao.getJwtAuthToken());
		assertNotNull(dao.getRole());
		assertNotNull(dao.getUsername());
		assertNull(dao.getServerCurrentTime());
		assertNull(dao.getTokenExpirationTime());
	}
	
}
