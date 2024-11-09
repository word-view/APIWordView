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

package cc.wordview.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WordViewResourceResolver {
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
