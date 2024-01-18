package cc.wordview.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cc.wordview.api.database.entity.LangWord;

public interface LangWordRepository extends CrudRepository<LangWord, Long> {
	Optional<LangWord> findByIdWord(Long idWord);

	List<LangWord> findByLang(String lang);
}
