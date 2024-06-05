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

package cc.wordview.api.request.lesson;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.Lesson;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonCreateRequest {
	public String title;
	public String difficulty;

	public Lesson toEntity() throws RequestValidationException {
		this.validate();

		Lesson newLesson = new Lesson();

		newLesson.setTitle(title);
		newLesson.setDifficulty(difficulty);

		return newLesson;
	}

	private void validate() throws RequestValidationException {
		if (isNull(title) || title.isEmpty()) {
			throw emptyOrNull("title");
		}

		if (isNull(difficulty) || difficulty.isEmpty()) {
			throw emptyOrNull("difficulty");
		}
	}
}
