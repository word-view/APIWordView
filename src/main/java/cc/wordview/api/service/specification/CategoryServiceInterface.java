package cc.wordview.api.service.specification;

import java.util.List;

import cc.wordview.api.database.entity.Category;
import cc.wordview.api.exception.NoSuchEntryException;

public interface CategoryServiceInterface extends ServiceInterface<Category, Category> {
	@Override
	Category getById(Long id) throws NoSuchEntryException;

	List<Category> getByTitle(String title) throws NoSuchEntryException;

	List<Category> getAll() throws NoSuchEntryException;
}
