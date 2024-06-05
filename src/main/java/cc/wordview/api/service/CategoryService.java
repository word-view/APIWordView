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

import cc.wordview.api.database.entity.Category;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.CategoryRepository;
import cc.wordview.api.service.specification.CategoryServiceInterface;

@Service
public class CategoryService implements CategoryServiceInterface {
	@Autowired
	private CategoryRepository repository;

	@Override
	public Category insert(Category entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public Category getById(Long id) throws NoSuchEntryException {
		Optional<Category> category = repository.findById(id);

		if (!category.isPresent()) {
			throw new NoSuchEntryException("Unable to find any category with this id");
		}

		return category.get();
	}

	@Override
	public List<Category> getByTitle(String title) throws NoSuchEntryException {
		Optional<List<Category>> category = repository.findByTitle(title);

		if (!category.isPresent()) {
			throw new NoSuchEntryException("Unable to find any category with this title");
		}

		return category.get();
	}

	@Override
	public List<Category> getAll() throws NoSuchEntryException {
		return (List<Category>) repository.findAll();
	}

}
