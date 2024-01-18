package cc.wordview.api.service.specification;

import java.util.List;

import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.NoSuchEntryException;

public interface WordServiceInterface extends ServiceInterface<Word> {
	Word getByNameId(String nameId) throws NoSuchEntryException;

	List<Word> getByIdLesson(Long idLesson) throws NoSuchEntryException;
}
