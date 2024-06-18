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

import cc.wordview.api.database.entity.NonAlphabeticWord;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.NonAlphabeticWordRepository;
import cc.wordview.api.service.specification.NonAlphabeticWordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NonAlphabeticWordService implements NonAlphabeticWordServiceInterface {
    @Autowired
    private NonAlphabeticWordRepository repository;

    @Override
    public NonAlphabeticWord getByName(String name) throws NoSuchEntryException {
        Optional<NonAlphabeticWord> word = repository.findByName(name);

        if (!word.isPresent()) {
            throw new NoSuchEntryException("Unable to find word with this name");
        }

        return word.get();
    }

    @Override
    public NonAlphabeticWord getById(Long id) throws NoSuchEntryException {
        Optional<NonAlphabeticWord> word = repository.findById(id);

        if (!word.isPresent()) {
            throw new NoSuchEntryException("Unable to find word with this id");
        }

        return word.get();
    }

    @Override
    public NonAlphabeticWord insert(NonAlphabeticWord entity) {
        return repository.save(entity);
    }
}
