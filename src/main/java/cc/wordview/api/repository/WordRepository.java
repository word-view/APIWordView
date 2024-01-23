package cc.wordview.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cc.wordview.api.database.entity.Word;

public interface WordRepository extends CrudRepository<Word, Long> {
	Optional<Word> findByName(String name);

	List<Word> findByIdLesson(Long idLesson);
}
