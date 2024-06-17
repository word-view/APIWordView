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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sapher.youtubedl.YoutubeDLException;

import cc.wordview.api.config.WordViewConfig;
import cc.wordview.api.service.specification.MusicServiceInterface;
import cc.wordview.api.util.FileReader;
import cc.wordview.api.util.StringUtil;
import cc.wordview.api.util.DLClient;
import cc.wordview.ytm.YoutubeApi;
import cc.wordview.ytm.response.LyricEntry;
import cc.wordview.ytm.response.SearchResult;
import cc.wordview.ytm.response.Video;

@Service
public class MusicService implements MusicServiceInterface {

        @Autowired
        private WordViewConfig config;

        YoutubeApi ytapi = new YoutubeApi();

        @Value("${wordview.ytm.api-key}")
        private String API_KEY;

        private Map<String, String> availableLyricsCache = new HashMap<>();

        @Override
        public Video getHistory() throws IOException {
                Video video = new Video();

                if (config.isProduction()) {
                        ytapi.setApiKey(API_KEY);
                        SearchResult result = ytapi.search("ano-yume-wo-nazotte", 1).get(0);

                        video.setId(result.getId().getVideoId());
                        video.setTitle(result.getSnippet().getTitle());
                        video.setArtist(result.getSnippet().getChannelTitle());
                        video.setCover(result.getSnippet().getThumbnails().getHigh().getUrl());
                } else {
                        video.setId("BCt9lS_Uv_Y");
                        video.setTitle("Firework of Shoes");
                        video.setArtist("Yorushika");
                        video.setCover("https://img.youtube.com/vi/BCt9lS_Uv_Y/0.jpg");
                }

                return video;
        }

        @Override
        public List<LyricEntry> getSubtitlesList(String id) throws YoutubeDLException {
                String availableLyrics;

                if (!availableLyricsCache.containsKey(id)) {
                        availableLyrics = DLClient.listSubtitles(id);
                        availableLyricsCache.put(id, availableLyrics);
                } else {
                        availableLyrics = availableLyricsCache.get(id);
                }

                String result = StringUtil.cutString(availableLyrics, "[info] Available subtitles for")
                                .replaceAll("vtt, ttml, srv3, srv2, srv1, json3", "");

                return LyricEntry.parse(result);
        }

        @Override
        public String getSubtitle(String id, String lang) throws YoutubeDLException, IOException {
                Path path = Paths.get(DLClient.getDefaultDirectory() + "/" + id + "." + lang + ".vtt");

                if (!Files.exists(path))
                        DLClient.downloadSubtitle(id, lang);

                String lyricsFile = FileReader.read(DLClient.getDefaultDirectory() + "/" + id + "." + lang + ".vtt");

                return lyricsFile;
        }

        @Override
        public Path download(String id) throws YoutubeDLException {
                Path file = Paths.get(DLClient.getDefaultDirectory() + "/" + id + ".mp3");

                if (!Files.exists(file))
                        DLClient.downloadVideo(id);

                return file;

        }

        @Override
        public List<SearchResult> search(String query, int maxResults) throws IOException {
                ytapi.setApiKey(API_KEY);
                return ytapi.search(query, 10);
        }

}
