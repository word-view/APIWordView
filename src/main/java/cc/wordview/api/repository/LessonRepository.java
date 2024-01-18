package cc.wordview.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cc.wordview.api.database.entity.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
	Optional<List<Lesson>> findByTitle(String title);

	Optional<List<Lesson>> findByDifficulty(String difficulty);
}
