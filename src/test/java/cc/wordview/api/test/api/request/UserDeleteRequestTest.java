package cc.wordview.api.test.api.request;

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.user.UserDeleteRequest;
import lombok.Getter;
import lombok.Setter;

import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Getter
@Setter
public class UserDeleteRequestTest {
	public String token;
	public String password;
	public String email;

	@Test
	void noException() throws Exception {
		assertDoesNotThrow(() -> {
			UserDeleteRequest request = new UserDeleteRequest();

			request.setEmail("arthur@gmail.com");
			request.setPassword("senha");

			request.toEntity();
		});
	}

	@Test
	void tokenEmpty() throws Exception {
		assertThrows(() -> {
			UserDeleteRequest request = new UserDeleteRequest();

			request.setEmail("arthur@gmail.com");
			request.setPassword("senha");

			request.toEntity();
		}, emptyOrNull("token").getMessage());
	}

	@Test
	void tokenNull() throws Exception {
		assertThrows(() -> {
			UserDeleteRequest request = new UserDeleteRequest();

			request.setEmail("arthur@gmail.com");
			request.setPassword("senha");

			request.toEntity();
		}, emptyOrNull("token").getMessage());
	}

	@Test
	void emailEmpty() throws Exception {
		assertThrows(() -> {
			UserDeleteRequest request = new UserDeleteRequest();

			request.setEmail("");
			request.setPassword("senha");

			request.toEntity();
		}, emptyOrNull("email").getMessage());
	}

	@Test
	void emailNull() throws Exception {
		assertThrows(() -> {
			UserDeleteRequest request = new UserDeleteRequest();

			request.setPassword("senha");

			request.toEntity();
		}, emptyOrNull("email").getMessage());
	}

	@Test
	void passwordEmpty() throws Exception {
		assertThrows(() -> {
			UserDeleteRequest request = new UserDeleteRequest();

			request.setEmail("arthur@gmail.com");
			request.setPassword("");

			request.toEntity();
		}, emptyOrNull("password").getMessage());
	}

	@Test
	void passswordNull() throws Exception {
		assertThrows(() -> {
			UserDeleteRequest request = new UserDeleteRequest();

			request.setEmail("arthur@gmail.com");

			request.toEntity();
		}, emptyOrNull("password").getMessage());
	}
}
