package cc.wordview.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.WordRepository;
import cc.wordview.api.service.specification.WordServiceInterface;

import static cc.wordview.api.service.ExceptionTemplate.*;

@Service
public class WordService extends Servicer implements WordServiceInterface {

        @Autowired
        private WordRepository repository;

        @Override
        public Word getById(Long id) throws NoSuchEntryException {
                return evaluatePresenceAndReturn(repository.findById(id), "id", id);
        }

        @Override
        public Word insert(Word entity) { return repository.save(entity); }

        @Override
        public Word getByNameId(String nameId) throws NoSuchEntryException {
                return evaluatePresenceAndReturn(
                        repository.findByNameId(nameId),
                        "nameId", nameId
                );
        }

        @Override
        public List<Word> getByIdLesson(Long idLesson) throws NoSuchEntryException {
                List<Word> words = repository.findByIdLesson(idLesson);

                if (words.isEmpty())
                        throw noSuchEntry("idLesson", idLesson);

                return words;
        }

}
