package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.ok;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapher.youtubedl.YoutubeDLException;

import cc.wordview.api.Constants;
import cc.wordview.api.config.WordViewConfig;
import cc.wordview.api.util.FileReader;
import cc.wordview.api.util.StringUtil;
import cc.wordview.api.util.ytdl.DLClient;
import cc.wordview.ytm.YoutubeApi;
import cc.wordview.ytm.response.LyricEntry;
import cc.wordview.ytm.response.SearchResult;
import cc.wordview.ytm.response.Video;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/music")
public class MusicController {
        private static final Logger logger = LoggerFactory.getLogger(MusicController.class);

        YoutubeApi ytapi = new YoutubeApi();

        @Value("${wordview.ytm.api-key}")
        private String API_KEY;

        @Autowired
        private WordViewConfig config;

        private Map<String, String> availableLyricsCache = new HashMap<>();

        // History is just being a placeholder here it does not provide the inteded
        // functionality yet
        @GetMapping("/history")
        public ResponseEntity<?> history() {
                return response(() -> {
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

                        return ok(video);
                });
        }

        @GetMapping("/lyrics/list")
        public ResponseEntity<?> lyricsList(@RequestParam String id) {
                return response(() -> {
                        String availableLyrics;

                        if (!availableLyricsCache.containsKey(id)) {
                                availableLyrics = DLClient.listSubtitles(id);
                                availableLyricsCache.put(id, availableLyrics);
                        } else {
                                availableLyrics = availableLyricsCache.get(id);
                        }

                        String result = StringUtil.cutString(availableLyrics, "[info] Available subtitles for")
                                        .replaceAll("vtt, ttml, srv3, srv2, srv1, json3", "");

                        return ok(LyricEntry.parse(result));
                });
        }

        @GetMapping("/lyrics")
        public ResponseEntity<?> lyrics(@RequestParam String id, @RequestParam String lang,
                        HttpServletResponse response) {
                return response(() -> {
                        Path path = Paths.get(DLClient.getDefaultDirectory() + "/" + id + "." + lang + ".vtt");

                        if (!Files.exists(path)) {
                                DLClient.downloadSubtitle(id, lang);
                        }

                        String lyricsFile = FileReader
                                        .read(DLClient.getDefaultDirectory() + "/" + id + "." + lang + ".vtt");

                        return ok(lyricsFile);
                });

        }

        @GetMapping("/search")
        public ResponseEntity<?> search(@RequestParam String q) {
                return response(() -> {
                        ytapi.setApiKey(API_KEY);
                        List<SearchResult> results = ytapi.search(q, 10);
                        return ok(results);
                });
        }

        @GetMapping("/download")
        public void download(@RequestParam String id, HttpServletResponse response)
                        throws YoutubeDLException, IOException {

                Path file = Paths.get(DLClient.getDefaultDirectory() + "/" + id + ".mp3");

                logger.info("Started streaming '" + id + "'");

                if (!Files.exists(file)) {
                        DLClient.downloadVideo(id);
                }

                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                        // Use the default media type
                        contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
                }

                response.setContentType(contentType);
                response.setContentLengthLong(Files.size(file));
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                                .filename(file.getFileName().toString(), StandardCharsets.UTF_8)
                                .build()
                                .toString());

                Files.copy(file, response.getOutputStream());
        }
}
