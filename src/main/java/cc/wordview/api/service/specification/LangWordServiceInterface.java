package cc.wordview.api.service.specification;

import cc.wordview.api.database.entity.LangWord;
import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.NoSuchEntryException;

public interface LangWordServiceInterface extends ServiceInterface<LangWord, LangWord> {
	LangWord wordToLangWord(Word word, String lang) throws NoSuchEntryException;
}
