package cc.wordview.api.test.api.request;

import cc.wordview.api.request.word.CreateRequest;
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
			request.setLang("jp");
			request.setLocalizedWord("車");
			request.setRomanizedWord("Kuruma");

			request.toEntity();
		});
	}

	@Test
	void idLessonEmpty() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setName("car");
			request.setLang("jp");
			request.setLocalizedWord("車");
			request.setRomanizedWord("Kuruma");

			request.toEntity();
		}, emptyOrNull("idLesson").getMessage());
	}

	@Test
	void nameEmpty() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setName("");
			request.setLang("jp");
			request.setLocalizedWord("車");
			request.setRomanizedWord("Kuruma");

			request.toEntity();
		}, emptyOrNull("name").getMessage());
	}

	@Test
	void nameNull() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setLang("jp");
			request.setLocalizedWord("車");
			request.setRomanizedWord("Kuruma");

			request.toEntity();
		}, emptyOrNull("name").getMessage());
	}

	@Test
	void langEmpty() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setName("car");
			request.setLang("");
			request.setLocalizedWord("車");
			request.setRomanizedWord("Kuruma");

			request.toEntity();
		}, emptyOrNull("lang").getMessage());
	}

	@Test
	void langNull() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setName("car");
			request.setLocalizedWord("車");
			request.setRomanizedWord("Kuruma");

			request.toEntity();
		}, emptyOrNull("lang").getMessage());
	}

	@Test
	void localizedWordEmpty() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setName("car");
			request.setLang("jp");
			request.setLocalizedWord("");
			request.setRomanizedWord("Kuruma");

			request.toEntity();
		}, emptyOrNull("localizedWord").getMessage());
	}

	@Test
	void localizedWordNull() throws Exception {
		assertThrows(() -> {
			CreateRequest request = new CreateRequest();

			request.setIdLesson(1L);
			request.setName("car");
			request.setLang("jp");
			request.setRomanizedWord("Kuruma");

			request.toEntity();
		}, emptyOrNull("localizedWord").getMessage());
	}

}
