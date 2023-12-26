package cc.wordview.api.service.specification;

import cc.wordview.api.exception.NoSuchEntryException;

import java.util.List;

import cc.wordview.api.database.entity.Lesson;

public interface LessonServiceInterface extends ServiceInterface<Lesson, Lesson> {
        @Override
        Lesson getById(Long id) throws NoSuchEntryException;

        List<Lesson> getByTitle(String title) throws NoSuchEntryException;

        List<Lesson> getByDifficulty(String difficulty) throws NoSuchEntryException;

        List<Lesson> getByCategoryId(Long id) throws NoSuchEntryException;
}
