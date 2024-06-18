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

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.WordRepository;
import cc.wordview.api.service.specification.WordServiceInterface;

@Service
public class WordService implements WordServiceInterface {

	@Autowired
	private WordRepository repository;

	@Override
	public Word getById(Long id) throws NoSuchEntryException {
		Optional<Word> word = repository.findById(id);

		if (!word.isPresent()) {
			throw new NoSuchEntryException("Unable to find word with this id");
		}

		return word.get();
	}

	@Override
	public Word insert(Word entity) {
		return repository.save(entity);
	}

	@Override
	public Word getByName(String name) throws NoSuchEntryException {
		Optional<Word> word = repository.findByName(name);

		if (!word.isPresent()) {
			throw new NoSuchEntryException("Unable to find word with this name_id");
		}

		return word.get();
	}
}
