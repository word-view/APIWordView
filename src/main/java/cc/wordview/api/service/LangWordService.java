package cc.wordview.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.LangWord;
import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.LangWordRepository;
import cc.wordview.api.service.specification.LangWordServiceInterface;

@Service
public class LangWordService implements LangWordServiceInterface {

	@Autowired
	private LangWordRepository repository;

	@Override
	public LangWord getById(Long id) throws NoSuchEntryException {
		Optional<LangWord> langword = repository.findById(id);

		if (!langword.isPresent()) {
			throw new NoSuchEntryException("Unable to find a langword with this id");
		}

		return langword.get();
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

		if (langWordToReturn == null) {
			throw new NoSuchEntryException("Unable to any word matching this langword");
		}

		return langWordToReturn;
	}

}
