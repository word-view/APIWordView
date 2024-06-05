/*
 * Copyright (c) 2024 Arthur Araujo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package cc.wordview.api.test.api.request;

import cc.wordview.api.request.word.WordCreateRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class WordRequestTest {
	@Test
	void noException() throws Exception {
		assertDoesNotThrow(() -> {
			WordCreateRequest request = new WordCreateRequest();

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
			WordCreateRequest request = new WordCreateRequest();

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
			WordCreateRequest request = new WordCreateRequest();

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
			WordCreateRequest request = new WordCreateRequest();

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
			WordCreateRequest request = new WordCreateRequest();

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
			WordCreateRequest request = new WordCreateRequest();

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
			WordCreateRequest request = new WordCreateRequest();

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
			WordCreateRequest request = new WordCreateRequest();

			request.setIdLesson(1L);
			request.setName("car");
			request.setLang("jp");
			request.setRomanizedWord("Kuruma");

			request.toEntity();
		}, emptyOrNull("localizedWord").getMessage());
	}

}
