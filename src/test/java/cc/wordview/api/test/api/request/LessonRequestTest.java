package cc.wordview.api.test.api.request;

import org.junit.jupiter.api.Test;

import cc.wordview.api.database.types.LessonDifficulty;
import cc.wordview.api.request.lesson.LessonCreateRequest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class LessonRequestTest {

	@Test
	void noException() throws Exception {
		assertDoesNotThrow(() -> {
			LessonCreateRequest request = new LessonCreateRequest();

			request.setTitle("Lesson 1");
			request.setDifficulty(LessonDifficulty.STARTER);

			request.toEntity();
		});
	}

	@Test
	void titleEmpty() throws Exception {
		assertThrows(() -> {
			LessonCreateRequest request = new LessonCreateRequest();

			request.setTitle("");
			request.setDifficulty(LessonDifficulty.STARTER);

			request.toEntity();
		}, emptyOrNull("title").getMessage());
	}

	@Test
	void titleNull() throws Exception {
		assertThrows(() -> {
			LessonCreateRequest request = new LessonCreateRequest();

			request.setDifficulty(LessonDifficulty.STARTER);

			request.toEntity();
		}, emptyOrNull("title").getMessage());
	}

	@Test
	void difficultyEmpty() throws Exception {
		assertThrows(() -> {
			LessonCreateRequest request = new LessonCreateRequest();

			request.setTitle("Lesson 1");
			request.setDifficulty("");

			request.toEntity();
		}, emptyOrNull("difficulty").getMessage());
	}

	@Test
	void difficultyNull() throws Exception {
		assertThrows(() -> {
			LessonCreateRequest request = new LessonCreateRequest();

			request.setTitle("Lesson 1");

			request.toEntity();
		}, emptyOrNull("difficulty").getMessage());
	}

	@Test
	void authorizationEmpty() throws Exception {
		assertThrows(() -> {
			LessonCreateRequest request = new LessonCreateRequest();

			request.setTitle("Lesson 1");
			request.setDifficulty(LessonDifficulty.STARTER);

			request.toEntity();
		}, emptyOrNull("authorization").getMessage());
	}

	@Test
	void authorizationNull() throws Exception {
		assertThrows(() -> {
			LessonCreateRequest request = new LessonCreateRequest();

			request.setTitle("Lesson 1");
			request.setDifficulty(LessonDifficulty.STARTER);

			request.toEntity();
		}, emptyOrNull("authorization").getMessage());
	}
}
