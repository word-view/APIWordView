package cc.wordview.api.test.api.request;

import cc.wordview.api.request.word.CreateRequest;
import cc.wordview.api.test.api.MockValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class WordRequestTest {
	@Test
	void noException() throws Exception {
		assertDoesNotThrow(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setName("car");
			request.setAuthorization(MockValues.ADMIN_TOKEN);

			request.toEntity();
		});
	}

	@Test
	void idLessonEmpty() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setName("car");
			request.setAuthorization(MockValues.ADMIN_TOKEN);

			request.toEntity();
		}, emptyOrNull("idLesson").getMessage());
	}

	@Test
	void nameEmpty() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setName("");
			request.setAuthorization(MockValues.ADMIN_TOKEN);

			request.toEntity();
		}, emptyOrNull("name").getMessage());
	}

	@Test
	void nameNull() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setAuthorization(MockValues.ADMIN_TOKEN);

			request.toEntity();
		}, emptyOrNull("name").getMessage());
	}

	@Test
	void authorizationEmpty() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setName("car");
			request.setAuthorization("");

			request.toEntity();
		}, emptyOrNull("authorization").getMessage());
	}

	@Test
	void authorizationNull() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setName("car");

			request.toEntity();
		}, emptyOrNull("authorization").getMessage());
	}
}
