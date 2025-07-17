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

package cc.wordview.api.util;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WordViewResourceResolver {
        private static final Logger logger = LoggerFactory.getLogger(WordViewResourceResolver.class);

        @Autowired
        private ResourceLoader resourceLoader;

        @Value("${wordview.dictionaries_path}")
        private String dictionariesPath;

        @Value("${wordview.images_path}")
        private String imagesPath;

        @Value("${wordview.lyrics_path}")
        private String lyricsPath;

        @Value("${wordview.phrases_path}")
        private String phrasesPath;

        @Value("${wordview.translations_path}")
        private String translationsPath;

        @PostConstruct
        public void debugShowPaths() {
                logger.info("""
                                Initialized with paths:
                                        Dictionaries Path: {}
                                        Images Path: {}
                                        Lyrics Path: {}
                                        Phrases Path: {}
                                        Translations Path: {}
                                """,
                        dictionariesPath, imagesPath, lyricsPath, phrasesPath, translationsPath);
        }

        public String getDictionariesPath() throws IOException {
                return resolvePathOrClasspath(dictionariesPath);
        }

        public String getImagesPath() throws IOException {
                return resolvePathOrClasspath(imagesPath);
        }

        public String getLyricsPath() throws IOException {
                return resolvePathOrClasspath(lyricsPath);
        }

        public String getPhrasesPath() throws IOException {
                return resolvePathOrClasspath(phrasesPath);
        }

        public String getTranslationsPath() throws IOException {
                return resolvePathOrClasspath(translationsPath);
        }

        /**
         * Checks if the path is at the classpath, and if so return the full URI
         * if it's a simple path it returns the path itself
         * @param path The path loaded from the .properties file
         */
        private String resolvePathOrClasspath(String path) throws IOException {
                String actualPath = path;

                if (path.startsWith("classpath:")) {
                        actualPath = resourceLoader.getResource(path).getURI().getPath();
                }

                return actualPath;
        }
}
