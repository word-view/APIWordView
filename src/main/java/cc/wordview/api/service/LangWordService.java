package cc.wordview.api.service;

import static cc.wordview.api.service.ExceptionTemplate.noSuchEntry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.LangWord;
import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.LangWordRepository;
import cc.wordview.api.service.specification.LangWordServiceInterface;

@Service
public class LangWordService extends Servicer implements LangWordServiceInterface {

	@Autowired
	private LangWordRepository repository;

	@Override
	public LangWord getById(Long id) throws NoSuchEntryException {
		return evaluatePresenceAndReturn(repository.findById(id), "id", id);
	}

	@Override
	public LangWord insert(LangWord entity) {
		return repository.save(entity);
	}

	@Override
	public LangWord wordToLangWord(Word word, String lang) throws NoSuchEntryException {
		List<LangWord> langWords = repository.findByLang(lang);

		LangWord langWordToReturn = null;

		for (LangWord langWord : langWords) {
			if (langWord.getIdWord() == word.getId())
				langWordToReturn = langWord;
		}

		if (langWordToReturn == null)
			throw noSuchEntry("word", word.getNameId());

		return langWordToReturn;
	}

}
