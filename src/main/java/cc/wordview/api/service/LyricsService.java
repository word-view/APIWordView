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

import cc.wordview.api.database.entity.VideoLyrics;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.service.specification.LyricsServiceInterface;
import cc.wordview.api.service.specification.VideoLyricsServiceInterface;
import cc.wordview.api.util.DownloaderImpl;
import cc.wordview.api.util.WordViewResourceResolver;
import cc.wordview.wordfind.exception.LyricsNotFoundException;
import cc.wordview.wordfind.WordFind;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.stream.StreamInfo;
import org.schabi.newpipe.extractor.stream.SubtitlesStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LyricsService implements LyricsServiceInterface {
        private static final Logger logger = LoggerFactory.getLogger(LyricsService.class);

        private static final StreamingService YTService;

        private final WordFind client = new WordFind();

        private final Map<String, String> lyrics = new HashMap<>();

        @Autowired
        private WordViewResourceResolver resourceResolver;

        @Autowired
        private VideoLyricsServiceInterface videoLyricsService;

        static {
                // initializing NewPipe here seems inappropriate, but I do it here anyway, so I don't have to initialize
                // both in the Application class and the tests at the same time.
                DownloaderImpl.init(null);
                NewPipe.init(DownloaderImpl.getInstance());

                try {
                        YTService = NewPipe.getService(0);
                } catch (ExtractionException e) {
                        throw new RuntimeException(e);
                }
        }


        @Override
        public String getLyrics(String id, String trackName, String artistName, String langTag) throws ExtractionException, IOException, LyricsNotFoundException {
                String lyrics;

                try {
                        lyrics = getLyricsWordView(id);
                } catch (Exception e) {
                        lyrics = getLyricsYT(id, langTag);

                        if (Objects.equals(lyrics, "")) {
                                lyrics = getLyricsExternal(trackName, artistName);
                        }
                }

                if (Objects.equals(lyrics, "WEBVTT\n\n# This WEBVTT was converted from LRC and might contain errors\n\n")) {
                        throw new LyricsNotFoundException("Unable to find lyrics for %s".formatted(trackName));
                }

                return lyrics;
        }

        private String getLyricsYT(String id, String langTag) throws ExtractionException, IOException {
                StreamInfo info = StreamInfo.getInfo(YTService, "https://youtube.com/watch?v=" + id);

                for (SubtitlesStream subtitle : info.getSubtitles()) {
                        if (subtitle.isAutoGenerated()) continue;

                        if (Objects.equals(subtitle.getLanguageTag(), langTag)) {
                                String url = subtitle.getContent().replace("&fmt=ttml", "&fmt=vtt");

                                RestTemplate restTemplate = new RestTemplate();
                                return restTemplate.getForObject(url, String.class);
                        }
                }

                return "";
        }

        private String getLyricsWordView(String id) throws IOException, LyricsNotFoundException, NoSuchEntryException {
                init();

                VideoLyrics videoLyrics = videoLyricsService.getByVideoId(id);

                String res = lyrics.get(videoLyrics.getLyricsFile() + ".vtt");

                if (res != null) return res;
                else
                        throw new LyricsNotFoundException("Could not find lyrics for %s".formatted(videoLyrics.getLyricsFile()));
        }

        private void init() throws IOException {
                if (!lyrics.isEmpty()) return;

                String lyricsPath = resourceResolver.getLyricsPath();

                try {
                        Files.walk(Path.of(lyricsPath))
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

        @Override
        public String getLyricsExternal(String trackName, String artistName) throws LyricsNotFoundException {
                return client.find(trackName, artistName, true, null, null);
        }
}
