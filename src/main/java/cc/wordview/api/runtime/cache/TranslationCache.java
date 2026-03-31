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

package cc.wordview.api.runtime.cache;

import cc.wordview.api.runtime.ResourceResolver;
import cc.wordview.api.service.util.SimpleTranslation;
import cc.wordview.api.service.util.Translation;
import cc.wordview.api.util.FileHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class TranslationCache extends ArrayCacheManager<Translation> {
    private static final Logger logger = LoggerFactory.getLogger(TranslationCache.class);

    @Autowired
    private ResourceResolver resourceResolver;

    @Override
    public void init() throws IOException {
        String translationsPath = resourceResolver.getTranslationsPath();

        List<Path> translationFiles = Files.list(Path.of(translationsPath)).toList();

        for (Path filePath : translationFiles) {
            String content = FileHelper.read(filePath.toFile());

            Type listType = new TypeToken<List<Translation>>() {
            }.getType();
            List<Translation> translationList = new Gson().fromJson(content, listType);

            array.addAll(translationList);
        }

        logger.info("Preloaded {} translations", array.size());
    }

    public ArrayList<SimpleTranslation> getTranslations(String lang, List<String> words) {
        ArrayList<SimpleTranslation> reqTranslations = new ArrayList<>();

        for (Translation translation : array) {
            String translatedWord = null;

            for (var aaa : translation.getTranslations()) {
                var tword = aaa.get(lang);
                if (tword != null) translatedWord = tword;
            }

            // it has no translations, skip
            if (translatedWord == null) continue;

            if (words.contains(translation.getParent())) {
                reqTranslations.add(new SimpleTranslation(
                        translation.getParent(),
                        translatedWord
                ));
            }
        }

        return reqTranslations;
    }
}
