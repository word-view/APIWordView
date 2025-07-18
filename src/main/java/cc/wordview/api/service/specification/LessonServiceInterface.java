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

package cc.wordview.api.service.specification;

import cc.wordview.api.database.entity.KnownWords;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.service.util.SimplePhrase;
import cc.wordview.api.service.util.SimpleTranslation;
import cc.wordview.gengolex.Language;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LessonServiceInterface {
        /**
         * Retrieves a phrase in the requested language that contains the given keyword in the specified word language.
         *
         * @param phraseLang the language code of the phrase (e.g., "en", "ja").
         * @param wordsLang the language code of the words used to match the keyword.
         * @param keyword the keyword to look for within the words of a phrase.
         * @return a JSON string representing a {@link SimplePhrase} containing the phrase and its words.
         * @throws IOException if loading phrase data fails.
         * @throws NoSuchEntryException if no matching phrase is found.
         */
        String getPhrase(String phraseLang, String wordsLang, String keyword) throws IOException, NoSuchEntryException;

        /**
         * Retrieves a list of phrases for the given list of keywords, in the specified phrase and word languages.
         *
         * @param phraseLang the language code of the phrase.
         * @param wordsLang the language code of the words used to match each keyword.
         * @param keywords the list of keywords to retrieve phrases for.
         * @return a list of JSON strings, each representing a {@link SimplePhrase}.
         * @throws IOException if loading phrase data fails.
         * @throws NoSuchEntryException if no phrases are found for any of the provided keywords.
         */
        ArrayList<String> getPhrases(String phraseLang, String wordsLang, List<String> keywords) throws IOException, NoSuchEntryException;

        /**
         * Retrieves translations for the specified list of words in the given language.
         *
         * @param lang the language code to retrieve translations for.
         * @param words the list of words to be translated.
         * @return a list of {@link SimpleTranslation} objects representing the translations.
         */
        ArrayList<SimpleTranslation> getTranslations(String lang, List<String> words) throws IOException, NoSuchEntryException;

        /**
         * Retrieves words that are known by the user in the specified language.
         *
         * @param userId the ID of the user.
         * @param lang the language code.
         * @return the {@link KnownWords} associated with the user and language.
         * @throws NoSuchEntryException if no entry is found for the given user and language.
         */
        KnownWords getKnownWords(Long userId, String lang) throws NoSuchEntryException;

        /**
         * Finds known words for the specified user and language, if available.
         *
         * @param userId the ID of the user.
         * @param lang the language code.
         * @return an {@link Optional} containing {@link KnownWords} if found, or empty if not.
         */
        Optional<KnownWords> findKnownWords(Long userId, String lang);

        /**
         * Adds words to the user's known words list for the specified language.
         * <p>
         * If no existing {@link KnownWords} entry exists for the user and language,
         * a new one is created.
         *
         * @param user the user whose known words are being added.
         * @param language the language for which the known words are relevant.
         * @param wordsToAdd the list of new words to add.
         */
        void addKnownWords(User user, Language language, List<String> wordsToAdd);
}
