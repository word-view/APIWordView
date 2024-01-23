package cc.wordview.api.request.category;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.Category;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequest {
	public String title;

	public Category toEntity() throws RequestValidationException {
		this.validate();

		Category newCategory = new Category();

		newCategory.setTitle(title);

		return newCategory;
	}

	private void validate() throws RequestValidationException {
		if (isNull(title) || title.isEmpty()) {
			throw emptyOrNull("title");
		}
	}
}
