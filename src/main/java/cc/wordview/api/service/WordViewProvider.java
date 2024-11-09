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

import cc.wordview.wordfind.exception.LyricsNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class WordViewProvider {
        private static final Logger logger = LoggerFactory.getLogger(WordViewProvider.class);

        private final Map<String, String> lyrics = new HashMap<>();

        public String find(String name, String lyricsPath, ResourceLoader resourceLoader) throws LyricsNotFoundException, IOException {
                init(lyricsPath, resourceLoader);

                String res = lyrics.get(name + ".vtt");

                if (res != null) return res;
                else throw new LyricsNotFoundException("Could not find lyrics for %s".formatted(name));
        }

        private void init(String lyricsPath, ResourceLoader resourceLoader) throws IOException {
                if (!lyrics.isEmpty()) return;

                String actualLyricsPath = lyricsPath;

                if (lyricsPath.startsWith("classpath:")) {
                        actualLyricsPath = resourceLoader.getResource(lyricsPath).getURI().getPath();
                }

                try {
                        Files.walk(Path.of(actualLyricsPath))
                                .filter(Files::isRegularFile)
                                .forEach(file -> {
                                        try {
                                                lyrics.put(file.getFileName().toString(), Files.readString(file));
                                                logger.info("Loaded lyrics %s".formatted(file.getFileName()));
                                        } catch (IOException e) {
                                                logger.error("Failed to read lyrics file", e);
                                        }
                                });
                } catch (IOException e) {
                        logger.error("Failed to read the lyrics directory", e);
                }
        }
}
