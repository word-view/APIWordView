package cc.wordview.api.repository;

import cc.wordview.api.database.entity.LanguageWord;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface LanguageWordRepository extends CrudRepository<LanguageWord, Long> {
        Optional<LanguageWord> findByIdWord(Long idWord);
}
