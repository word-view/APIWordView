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
import cc.wordview.gengolex.Language;
import cc.wordview.gengolex.LanguageNotFoundException;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class FeedCache extends HashMapCacheManager<String> {
    private static final Logger logger = LoggerFactory.getLogger(FeedCache.class);

    @Autowired
    private ResourceResolver resourceResolver;

    // This won't throw language not found, just quiet the compiler here
    @SneakyThrows(LanguageNotFoundException.class)
    @Override
    public void init() throws IOException {
        String feedsPath = resourceResolver.getFeedsPath();

        String[] langTag = {
                Language.ENGLISH.getTag(),
                Language.PORTUGUESE.getTag(),
                Language.JAPANESE.getTag()
        };

        for (var tag : langTag) {
            try (InputStream feedFile = new FileInputStream(feedsPath + "/" + tag + ".json")) {
                String feedJson = new String(feedFile.readAllBytes(), StandardCharsets.UTF_8)
                        .replace("\n", "").trim();

                map.put(tag, feedJson);
            } catch (FileNotFoundException e) {
                logger.warn("Feed is missing for language {}", Language.Companion.byTag(tag).name());
            }
        }

        logger.info("Preloaded {} feeds", map.size());
    }
}
