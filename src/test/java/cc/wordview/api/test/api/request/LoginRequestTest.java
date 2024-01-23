package cc.wordview.api.test.api.request;

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.user.UserCreateRequest;
import cc.wordview.api.request.user.UserLoginRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class LoginRequestTest {
	@Test
	void noException() throws Exception {
		assertDoesNotThrow(() -> {
			UserLoginRequest request = new UserLoginRequest();

			request.setEmail("arthur@gmail.com");
			request.setPassword("senha23213");

			request.toEntity();
		});
	}

	@Test
	void emailInvalid() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setPassword("senha23213");
			request.setEmail("arthur@cmake.com");

			request.toEntity();
		}, invalid("email").getMessage());
	}

	@Test
	void emailEmpty() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setPassword("senha23213");
			request.setEmail("");

			request.toEntity();
		}, emptyOrNull("email").getMessage());
	}

	@Test
	void emailNull() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setPassword("senha23213");

			request.toEntity();
		}, emptyOrNull("email").getMessage());
	}

	@Test
	void passwordEmpty() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setPassword("");
			request.setEmail("arthur@gmail.com");

			request.toEntity();
		}, emptyOrNull("password").getMessage());
	}

	@Test
	void passwordNull() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setEmail("arthur@gmail.com");

			request.toEntity();
		}, emptyOrNull("password").getMessage());
	}

}
