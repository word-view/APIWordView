package cc.wordview.api.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.Lesson;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.service.specification.LessonServiceInterface;
import cc.wordview.api.repository.LessonRepository;

import static cc.wordview.api.service.ExceptionTemplate.*;

@Service
public class LessonService extends Servicer implements LessonServiceInterface {
        @Autowired
        private LessonRepository repository;

        @Override
        public Lesson getById(Long id) throws NoSuchEntryException {
                return evaluatePresenceAndReturn(repository.findById(id), "id", id);
        }

        @Override
        public Lesson insert(Lesson entity) { return repository.save(entity); }

        @Override
        public List<Lesson> getByTitle(String title) throws NoSuchEntryException {
                List<Lesson> lessons = new ArrayList<>();

                for (Lesson entry : repository.findAll()) {
                        if (entry.getTitle().startsWith(title, 0))
                                lessons.add(entry);
                }

                if (lessons.isEmpty())
                        throw noSuchEntry("title", title);

                return lessons;
        }

        @Override
        public List<Lesson> getByDifficulty(String difficulty) throws NoSuchEntryException {
                return evaluatePresenceAndReturn(repository.findByDifficulty(difficulty),
                                "difficulty", difficulty);
        }

        @Override
        public List<Lesson> getByCategoryId(Long id) throws NoSuchEntryException {
                List<Lesson> lessons = new ArrayList<>();

                for (Lesson entry : repository.findAll()) {
                        if (entry.getIdCategory() == id) {
                                lessons.add(entry);
                        }
                }

                if (lessons.isEmpty())
                        throw noSuchEntry("category", id);

                return lessons;
        }

}
