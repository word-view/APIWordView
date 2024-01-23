package cc.wordview.api.test.api.request;

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.category.CategoryCreateRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class CategoryRequestTest {
	@Test
	void noException() throws Exception {
		assertDoesNotThrow(() -> {
			CategoryCreateRequest request = new CategoryCreateRequest();

			request.setTitle("Cidade");

			request.toEntity();
		});
	}

	@Test
	void titleEmpty() throws Exception {
		assertThrows(() -> {
			CategoryCreateRequest request = new CategoryCreateRequest();

			request.setTitle("");

			request.toEntity();
		}, emptyOrNull("title").getMessage());
	}

	@Test
	void titleNull() throws Exception {
		assertThrows(() -> {
			CategoryCreateRequest request = new CategoryCreateRequest();

			request.toEntity();
		}, emptyOrNull("title").getMessage());
	}

	@Test
	void authorizationEmpty() throws Exception {
		assertThrows(() -> {
			CategoryCreateRequest request = new CategoryCreateRequest();

			request.setTitle("Cidade");

			request.toEntity();
		}, emptyOrNull("authorization").getMessage());
	}

	@Test
	void authorizationNull() throws Exception {
		assertThrows(() -> {
			CategoryCreateRequest request = new CategoryCreateRequest();

			request.setTitle("Cidade");

			request.toEntity();
		}, emptyOrNull("authorization").getMessage());
	}
}
