package cc.wordview.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.LanguageWord;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.LanguageWordRepository;
import cc.wordview.api.service.specification.LanguageWordServiceInterface;

@Service
public class LanguageWordService extends Servicer implements LanguageWordServiceInterface {

        @Autowired
        private LanguageWordRepository repository;

        @Override
        public LanguageWord getById(Long id) throws NoSuchEntryException {
                return evaluatePresenceAndReturn(repository.findById(id), "id", id);
        }

        @Override
        public LanguageWord insert(LanguageWord entity) { return repository.save(entity); }

}
