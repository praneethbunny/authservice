package com.tweetapp.userauthorizationservice.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserAcountExceptionTest {

	private UserAcountException ex = new UserAcountException("error");
	
	@Test
	void testMessageSetter() {
		assertThat(ex).isNotNull();
	}	
}
