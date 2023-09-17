package cc.wordview.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.WordRepository;
import cc.wordview.api.service.specification.WordServiceInterface;

import static cc.wordview.api.service.ExceptionTemplate.*;

@Service
public class WordService implements WordServiceInterface {

        @Autowired
        private WordRepository repository;

        @Override
        public Word getById(Long id) throws NoSuchEntryException {
                Optional<Word> word = repository.findById(id);

                if (!word.isPresent())
                        throw noSuchEntry("id", id);

                return word.get();
        }

        @Override
        public Word insert(Word entity) { return repository.save(entity); }

        @Override
        public Word getByNameId(String nameId) throws NoSuchEntryException {
                Optional<Word> word = repository.findByNameId(nameId);

                if (!word.isPresent())
                        throw noSuchEntry("nameId", nameId);

                return word.get();
        }

        @Override
        public List<Word> getByIdLesson(Long idLesson) throws NoSuchEntryException {
                List<Word> words = repository.findByIdLesson(idLesson);

                if (words.isEmpty())
                        throw noSuchEntry("idLessons", idLesson);

                return words;
        }

}
