package cc.wordview.api.repository;

import cc.wordview.api.database.entity.LangWord;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface LangWordRepository extends CrudRepository<LangWord, Long> {
        Optional<LangWord> findByIdWord(Long idWord);
}
