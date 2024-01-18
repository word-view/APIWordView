package cc.wordview.api.request.word;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRequest {
	public String nameId;
	public Long idLesson;
	public String authorization;

	public Word toEntity() throws RequestValidationException {
		this.validate();

		Word newWord = new Word();

		newWord.setNameId(nameId);
		newWord.setIdLesson(idLesson);

		return newWord;
	}

	private void validate() throws RequestValidationException {
		if (isNull(idLesson)) {
			throw emptyOrNull("idLesson");
		}

		if (isNull(nameId) || nameId.isEmpty()) {
			throw emptyOrNull("nameId");
		}

		if (isNull(idLesson)) {
			throw emptyOrNull("difficulty");
		}

		if (isNull(authorization) || authorization.isEmpty()) {
			throw emptyOrNull("authorization");
		}

	}
}
