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

import cc.wordview.api.database.entity.VideoLyrics;
import cc.wordview.api.service.specification.VideoLyricsServiceInterface;
import cc.wordview.api.util.WordViewResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LyricsCache extends HashMapCacheManager<String> {
    private static final Logger logger = LoggerFactory.getLogger(LyricsCache.class);

    @Autowired
    private WordViewResourceResolver resourceResolver;

    @Autowired
    private VideoLyricsServiceInterface videoLyricsService;

    @Override
    public void init() throws IOException {
        String lyricsPath = resourceResolver.getLyricsPath();
        Path filepath = Path.of(lyricsPath);

        Map<String, String> fileToContent = new HashMap<>();

        try {
            Files.walk(filepath)
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            fileToContent.put(file.getFileName().toString(), Files.readString(file));
                        } catch (IOException e) {
                            logger.error("Failed to read lyrics file, ignoring this file", e);
                        }
                    });


            List<VideoLyrics> videoLyrics = videoLyricsService.getAll();

            for (var videoLyric : videoLyrics) {
                String content = fileToContent.get(videoLyric.getLyricsFile() + ".vtt");
                map.put(videoLyric.getVideoId(), content);
            }

            logger.info("Preloaded {} lyrics", map.size());
        } catch (IOException e) {
            logger.error("Failed to read the lyrics path", e);
        }
    }

    public void put(String id, String lyrics) {
        if (map.containsKey(id))
            return;

        logger.info("Adding {} to cache, there are now {} lyrics", id, map.size() + 1);
        map.put(id, lyrics);
    }
}
