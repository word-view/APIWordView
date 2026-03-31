/*
 * Copyright (c) 2025 Arthur Araujo
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

package cc.wordview.api.service.implementation;

import cc.wordview.api.database.entity.KnownWords;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.KnownWordsRepository;
import cc.wordview.api.runtime.PhraseCache;
import cc.wordview.api.runtime.TranslationCache;
import cc.wordview.api.service.LessonServiceInterface;
import cc.wordview.api.service.util.SimpleTranslation;
import cc.wordview.api.util.ArrayUtil;
import cc.wordview.gengolex.Language;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService implements LessonServiceInterface {
    @Autowired
    private KnownWordsRepository repository;

    @Autowired
    private PhraseCache phraseCache;

    @Autowired
    private TranslationCache translationCache;

    @Override
    public String getPhrase(String phraseLang, String wordsLang, String keyword) throws NoSuchEntryException {
        return phraseCache.getRandomPhrase(phraseLang, wordsLang, keyword);
    }

    @Override
    public ArrayList<String> getPhrases(String phraseLang, String wordsLang, List<String> keywords) throws NoSuchEntryException {
        return phraseCache.getMultiplePhrases(phraseLang, wordsLang, keywords);
    }

    @Override
    public ArrayList<SimpleTranslation> getTranslations(String lang, List<String> words) {
        return translationCache.getTranslations(lang, words);
    }

    @Override
    public KnownWords getKnownWords(Long userId, String lang) throws NoSuchEntryException {
        Optional<KnownWords> knownWords = repository.findByUserIdAndLang(userId, lang);

        if (!knownWords.isPresent()) {
            throw new NoSuchEntryException("Unable to find known words for this language");
        }

        return knownWords.get();
    }

    @Override
    public Optional<KnownWords> findKnownWords(Long userId, String lang) {
        return repository.findByUserIdAndLang(userId, lang);
    }

    @Override
    public void addKnownWords(User user, Language language, List<String> wordsToAdd) {
        Optional<KnownWords> knownWordsOpt = findKnownWords(user.getId(), language.getTag());
        KnownWords knownWords = knownWordsOpt.orElseGet(KnownWords::new);

        knownWords.setUserId(user.getId());
        knownWords.setLang(language.getTag());

        String wordsKnownList = knownWords.getWords();

        if (wordsKnownList != null) {
            List<String> alreadyKnownWords = Arrays.stream(knownWords.getWords().split(",")).toList();
            wordsToAdd.addAll(alreadyKnownWords);
        }

        ArrayList<String> wordsWithoutDuplicates = ArrayUtil.withoutDuplicates(wordsToAdd);

        knownWords.setWords(String.join(",", wordsWithoutDuplicates));

        insertKnownWords(knownWords);
    }

    private void insertKnownWords(KnownWords entity) {
        repository.save(entity);
    }

    @PostConstruct
    private void preload() throws IOException {
        phraseCache.init();
        translationCache.init();
    }
}
