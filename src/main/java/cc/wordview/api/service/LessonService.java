/*
 * Copyright (c) 2024 Arthur Araujo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package cc.wordview.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.Lesson;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.LessonRepository;
import cc.wordview.api.service.specification.LessonServiceInterface;

@Service
public class LessonService implements LessonServiceInterface {
	@Autowired
	private LessonRepository repository;

	@Override
	public Lesson getById(Long id) throws NoSuchEntryException {
		Optional<Lesson> lesson = repository.findById(id);

		if (!lesson.isPresent()) {
			throw new NoSuchEntryException("Unable to find lesson with this id");
		}

		return lesson.get();
	}

	@Override
	public Lesson insert(Lesson entity) {
		return repository.save(entity);
	}

	@Override
	public List<Lesson> getByTitle(String title) throws NoSuchEntryException {
		List<Lesson> lessons = new ArrayList<>();

		for (Lesson entry : repository.findAll()) {
			if (entry.getTitle().startsWith(title, 0))
				lessons.add(entry);
		}

		if (lessons.isEmpty()) {
			throw new NoSuchEntryException("Unable to find any lessons with this title");
		}

		return lessons;
	}

	@Override
	public List<Lesson> getByDifficulty(String difficulty) throws NoSuchEntryException {
		Optional<List<Lesson>> lessons = repository.findByDifficulty(difficulty);

		if (!lessons.isPresent() || lessons.get().size() == 0) {
			throw new NoSuchEntryException("Unable to find any lessons with this difficulty");
		}

		return lessons.get();
	}

	@Override
	public List<Lesson> getByCategoryId(Long id) throws NoSuchEntryException {
		List<Lesson> lessons = new ArrayList<>();

		for (Lesson entry : repository.findAll()) {
			if (entry.getIdCategory() == id) {
				lessons.add(entry);
			}
		}

		if (lessons.isEmpty()) {
			throw new NoSuchEntryException("Unable to find any lessons with this category_id");
		}

		return lessons;
	}

}
