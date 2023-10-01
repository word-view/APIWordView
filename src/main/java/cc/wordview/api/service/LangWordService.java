package cc.wordview.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.LangWord;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.LangWordRepository;
import cc.wordview.api.service.specification.LangWordServiceInterface;

@Service
public class LangWordService extends Servicer implements LangWordServiceInterface {

        @Autowired
        private LangWordRepository repository;

        @Override
        public LangWord getById(Long id) throws NoSuchEntryException {
                return evaluatePresenceAndReturn(repository.findById(id), "id", id);
        }

        @Override
        public LangWord insert(LangWord entity) { return repository.save(entity); }

}
