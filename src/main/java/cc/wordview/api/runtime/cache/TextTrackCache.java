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

import cc.wordview.api.database.entity.TextTrack;
import cc.wordview.api.repository.TextTracksRepository;
import cc.wordview.api.runtime.ResourceResolver;
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
import java.util.stream.Stream;

@Component
public class TextTrackCache extends HashMapCacheManager<String> {
    private static final Logger logger = LoggerFactory.getLogger(TextTrackCache.class);

    @Autowired
    private ResourceResolver resourceResolver;

    @Autowired
    private TextTracksRepository repository;

    @Override
    public void init() throws IOException {
        String subtitlesPath = resourceResolver.getTextTracksPath();
        Path filepath = Path.of(subtitlesPath);

        Map<String, String> fileToContent = new HashMap<>();

        try (Stream<Path> paths = Files.walk(filepath)) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                try {
                    fileToContent.put(file.getFileName().toString(), Files.readString(file));
                } catch (IOException e) {
                    logger.error("Failed to read subtitle file, ignoring this file", e);
                }
            });

            List<TextTrack> textTracks = (List<TextTrack>) repository.findAll();

            for (var subtitle : textTracks) {
                String content = fileToContent.get(subtitle.getFile());
                map.put(subtitle.getVideoId(), content);
            }

            logger.info("Preloaded {} text tracks", map.size());
        } catch (IOException e) {
            logger.error("Failed to read the text tracks path", e);
        }
    }

    public void put(String id, String subtitle) {
        if (map.containsKey(id))
            return;
        logger.info("Adding {} to cache, there are now {} text tracks", id, map.size() + 1);
        map.put(id, subtitle);
    }
}
