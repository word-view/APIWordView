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

package cc.wordview.api.runtime;

import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.service.util.Phrase;
import cc.wordview.api.service.util.SimplePhrase;
import cc.wordview.api.util.ArrayUtil;
import cc.wordview.api.util.FileHelper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhraseCache extends ArrayCacheManager<Phrase> {
    private static final Logger logger = LoggerFactory.getLogger(PhraseCache.class);

    @Autowired
    private ResourceResolver resourceResolver;

    @Override
    public void init() throws IOException {
        String phrasesPath = resourceResolver.getPhrasesPath();
        List<Path> phraseFiles = Files.list(Path.of(phrasesPath)).toList();

        for (Path filePath : phraseFiles) {
            String content = FileHelper.read(filePath.toFile());
            array.add(new Gson().fromJson(content, Phrase.class));
        }

        logger.info("Preloaded {} phrases", array.size());
    }

    /**
     * Fetches from the cache and returns a randomly picked SimplePhrase serialized as a JSON string
     */
    public String getRandomPhrase(String phraseLang, String wordsLang, String keyword) throws NoSuchEntryException {
        ArrayList<SimplePhrase> possibilities = new ArrayList<>();

        for (Phrase phrase : array) {
            List<String> words = phrase.getWords().getFirst().get(wordsLang);
            String phraseText = phrase.getPhrases().get(phraseLang);

            if (phraseText == null) continue;
            if (words == null) continue;
            if (!words.contains(keyword)) continue;

            possibilities.add(new SimplePhrase(phraseText, words));
        }

        if (possibilities.isEmpty())
            throw new NoSuchEntryException("Could not find any phrases matching these parameters");

        SimplePhrase picked = ArrayUtil.random(possibilities);

        return new Gson().toJson(picked);
    }

    public ArrayList<String> getMultiplePhrases(String phraseLang, String wordsLang, List<String> keywords) throws NoSuchEntryException {
        ArrayList<String> phrases = new ArrayList<>();

        for (String keyword : keywords) {
            try {
                String phrase = getRandomPhrase(phraseLang, wordsLang, keyword);
                phrases.add(phrase);
            } catch (NoSuchEntryException ignored) {
                // skip as not finding one phrase shouldn't stop the flow
            }
        }

        if (phrases.isEmpty())
            throw new NoSuchEntryException("Couldn't find any phrases matching these keywords");

        return phrases;
    }
}
