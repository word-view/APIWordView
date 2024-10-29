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

import cc.wordview.api.service.specification.LyricsServiceInterface;
import cc.wordview.api.util.DownloaderImpl;
import cc.wordview.wordfind.Lrc2Vtt;
import cc.wordview.wordfind.LyricsNotFoundException;
import cc.wordview.wordfind.LyricsProvider;
import cc.wordview.wordfind.WordFindClient;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.stream.StreamInfo;
import org.schabi.newpipe.extractor.stream.SubtitlesStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

@Service
public class LyricsService implements LyricsServiceInterface {
        private static final Logger logger = LoggerFactory.getLogger(LyricsService.class);

        private static final StreamingService YTService;

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
        public String getLyrics(String id, String langTag, String query) throws ExtractionException, IOException, LyricsNotFoundException {
                String lyrics = getLyricsYT(id, langTag);

                if (Objects.equals(lyrics, "")) {
                        logger.warn("Unable to find any lyrics for '%s' with lang '%s' on youtube.".formatted(id, langTag));
                        lyrics = getLyricsExternal(query);
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

        @Override
        public String getLyricsExternal(String query) throws IOException, LyricsNotFoundException {
                String result;

                try {
                        result = WordFindClient.search(query);
                } catch (LyricsNotFoundException e) {
                        logger.warn("Unable to find any lyrics for \"%s\" in Musixmatch, retrying with NetEase.".formatted(query));
                        result = WordFindClient.search(query, LyricsProvider.NETEASE);
                }

                StringBuffer vttLyrics = Lrc2Vtt.convert(result);

                return vttLyrics.toString();
        }
}
