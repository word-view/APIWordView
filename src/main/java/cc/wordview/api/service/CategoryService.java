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
