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
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.service.util.SimpleTranslation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LessonServiceInterface {
        String getPhrase(String phraseLang, String wordsLang, String keyword) throws IOException, NoSuchEntryException;
        ArrayList<SimpleTranslation> getTranslations(String lang, List<String> words) throws IOException, NoSuchEntryException;
        KnownWords getKnownWords(Long userId, String lang) throws NoSuchEntryException;
        Optional<KnownWords> optionalGetKnownWords(Long userId, String lang);
        KnownWords insertKnownWords(KnownWords knownWords);
}
