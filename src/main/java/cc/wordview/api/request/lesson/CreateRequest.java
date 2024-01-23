package cc.wordview.api.request.lesson;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.Lesson;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRequest {
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
