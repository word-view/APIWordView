package cc.wordview.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.Lesson;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.service.specification.LessonServiceInterface;
import cc.wordview.api.repository.LessonRepository;

import static cc.wordview.api.service.ExceptionTemplate.*;

@Service
public class LessonService implements LessonServiceInterface {
        @Autowired
        private LessonRepository repository;

        @Override
        public Lesson getById(Long id) throws NoSuchEntryException {
                Optional<Lesson> lesson = repository.findById(id);

                if (!lesson.isPresent())
                        throw noSuchEntry("id", id);

                return lesson.get();
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
                Optional<List<Lesson>> lesson = repository.findByDifficulty(difficulty);

                if (!lesson.isPresent())
                        throw noSuchEntry("difficulty", difficulty);

                return lesson.get();
        }

}
