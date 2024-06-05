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
