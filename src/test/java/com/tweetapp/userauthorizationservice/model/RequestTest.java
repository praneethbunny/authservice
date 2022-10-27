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
public class RequestTest {

	@Mock
	private Request request;
	
private AutoCloseable closable;
	
	@BeforeEach
	public void setup() {
		closable = MockitoAnnotations.openMocks(this);
		request = new Request();
		request.setPassword("pass");
		request.setUsername("test");
	}

	@AfterEach
	void closeService() throws Exception {
		closable.close();
	}
	
	@Test
	public void gettersTest() {
		assertEquals("pass",request.getPassword());
		assertEquals("test",request.getUsername());
	}


}
