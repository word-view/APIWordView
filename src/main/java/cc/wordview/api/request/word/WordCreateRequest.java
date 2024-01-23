package cc.wordview.api.request.word;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordCreateRequest {
	public Long idLesson;
	public String name;
	public String lang;
	public String localizedWord;
	public String romanizedWord;

	public Word toEntity() throws RequestValidationException {
		this.validate();

		Word newWord = new Word();

		newWord.setIdLesson(idLesson);
		newWord.setName(name);
		newWord.setLang(lang);
		newWord.setLocalizedWord(localizedWord);
		newWord.setRomanizedWord(romanizedWord);

		return newWord;
	}

	private void validate() throws RequestValidationException {
		if (isNull(idLesson)) {
			throw emptyOrNull("idLesson");
		}

		if (isNull(name) || name.isEmpty()) {
			throw emptyOrNull("name");
		}

		if (isNull(idLesson)) {
			throw emptyOrNull("difficulty");
		}

		if (isNull(lang) || lang.isEmpty()) {
			throw emptyOrNull("lang");
		}

		if (isNull(localizedWord) || localizedWord.isEmpty()) {
			throw emptyOrNull("localizedWord");
		}
	}
}
