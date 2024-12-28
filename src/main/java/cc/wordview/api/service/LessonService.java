/*
 * Copyright (c) 2024 Arthur Araujo
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

package cc.wordview.api.service;

import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.service.specification.LessonServiceInterface;
import cc.wordview.api.service.util.Phrase;
import cc.wordview.api.service.util.SimplePhrase;
import cc.wordview.api.util.ArrayUtil;
import cc.wordview.api.util.FileHelper;
import cc.wordview.api.util.WordViewResourceResolver;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService implements LessonServiceInterface {
        private static final Logger logger = LoggerFactory.getLogger(LessonService.class);

        @Autowired
        private WordViewResourceResolver resourceResolver;

        private final ArrayList<Phrase> phrases = new ArrayList<>();

        @Override
        public String getPhrase(String phraseLang, String wordsLang, String keyword) throws IOException, NoSuchEntryException {
                preloadAllPhrases();

                ArrayList<SimplePhrase> availablePhrases = new ArrayList<>();

                for (Phrase phrase : phrases) {
                        List<String> words = phrase.getWords().getFirst().get(wordsLang);
                        String phraseText = phrase.getPhrases().get(phraseLang);

                        if (phraseText == null) continue;
                        if (words == null) continue;
                        if (!words.contains(keyword)) continue;

                        availablePhrases.add(new SimplePhrase(phraseText, words));
                }

                if (availablePhrases.isEmpty())
                        throw new NoSuchEntryException("Could not find any phrases matching these parameters");

                SimplePhrase chosen = ArrayUtil.random(availablePhrases);

                return new Gson().toJson(chosen);
        }

        private void preloadAllPhrases() throws IOException {
                if (!phrases.isEmpty()) return;

                String phrasesPath = resourceResolver.getPhrasesPath();

                List<Path> phraseFiles = Files.list(Path.of(phrasesPath)).toList();

                for (Path filePath : phraseFiles) {
                        String content = FileHelper.read(filePath.toFile());

                        phrases.add(new Gson().fromJson(content, Phrase.class));
                }

                logger.info("Loaded %s phrase files.".formatted(phraseFiles.size()));
        }
}
