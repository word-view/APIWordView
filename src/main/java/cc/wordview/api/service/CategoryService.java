package cc.wordview.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.Category;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.CategoryRepository;
import cc.wordview.api.service.specification.CategoryServiceInterface;

@Service
public class CategoryService extends Servicer implements CategoryServiceInterface {
	@Autowired
	private CategoryRepository repository;

	@Override
	public Category insert(Category entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public Category getById(Long id) throws NoSuchEntryException {
		return evaluatePresenceAndReturn(repository.findById(id), "id", id);
	}

	@Override
	public List<Category> getByTitle(String title) throws NoSuchEntryException {
		return evaluatePresenceAndReturn(repository.findByTitle(title), "title", title);
	}

	@Override
	public List<Category> getAll() throws NoSuchEntryException {
		return (List<Category>) repository.findAll();
	}

}
