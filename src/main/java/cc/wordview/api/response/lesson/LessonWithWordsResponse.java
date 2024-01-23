package cc.wordview.api.response.lesson;

import java.util.ArrayList;
import java.util.List;

import cc.wordview.api.database.entity.Lesson;
import cc.wordview.api.database.entity.Word;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonWithWordsResponse {
	private String title;
	private String difficulty;
	private List<WordWithoutId> words = new ArrayList<>();

	public LessonWithWordsResponse(Lesson lesson, List<Word> words) {
		setTitle(lesson.getTitle());
		setDifficulty(lesson.getDifficulty());
		for (Word word : words) {
			this.words.add(new WordWithoutId(word));
		}
	}
}
