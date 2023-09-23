package cc.wordview.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.LanguageWord;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.LanguageWordRepository;
import cc.wordview.api.service.specification.LanguageWordServiceInterface;

import static cc.wordview.api.service.ExceptionTemplate.*;

@Service
public class LanguageWordService implements LanguageWordServiceInterface {
        @Autowired
        private LanguageWordRepository repository;

        @Override
        public LanguageWord getById(Long id) throws NoSuchEntryException {
                // super().evaluatePresenceAndReturn(langWord);
                Optional<LanguageWord> langWord = repository.findById(id);

                if (!langWord.isPresent())
                        throw noSuchEntry("id", id);

                return langWord.get();
        }

        @Override
        public LanguageWord insert(LanguageWord entity) {
                return repository.save(entity);
        }

}
