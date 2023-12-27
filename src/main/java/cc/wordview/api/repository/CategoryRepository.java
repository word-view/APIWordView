package cc.wordview.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cc.wordview.api.database.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
        Optional<List<Category>> findByTitle(String title);
}
