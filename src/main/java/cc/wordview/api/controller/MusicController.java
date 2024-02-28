package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.ok;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;

import cc.wordview.api.Constants;
import cc.wordview.api.util.FileReader;
import cc.wordview.api.util.StringUtil;
import cc.wordview.ytm.YoutubeApi;
import cc.wordview.ytm.response.SearchResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/music")
public class MusicController {
        YoutubeApi ytapi = new YoutubeApi();

        @Value("${wordview.ytm.api-key}")
        private String API_KEY;

        @GetMapping("/lyrics/list")
        public ResponseEntity<?> lyrics(@RequestParam String id) {
                return response(() -> {
                        String musicUrl = "https://www.youtube.com/watch?v=" + id;
                        String directory = System.getProperty("java.io.tmpdir");

                        YoutubeDLRequest request = new YoutubeDLRequest(musicUrl, directory);

                        request.setOption("ignore-errors");
                        request.setOption("list-subs");
                        request.setOption("skip-download");
                        request.setOption("retries", 10);

                        YoutubeDLResponse response = YoutubeDL.execute(request);

                        String result = StringUtil.cutString(response.getOut(), "[info] Available subtitles for");

                        return ok(result);
                });
        }

        @GetMapping("/lyrics")
        public ResponseEntity<?> lyrics(@RequestParam String id, @RequestParam String lang,
                        HttpServletResponse response) {
                return response(() -> {
                        String musicUrl = "https://www.youtube.com/watch?v=" + id;
                        String directory = System.getProperty("java.io.tmpdir");

                        YoutubeDLRequest request = new YoutubeDLRequest(musicUrl, directory);

                        request.setOption("ignore-errors");
                        request.setOption("write-sub");
                        request.setOption("sub-lang", lang);
                        request.setOption("skip-download");
                        request.setOption("output", "%(id)s");
                        request.setOption("retries", 10);

                        YoutubeDL.execute(request);

                        String lyricsFile = FileReader.read(directory + "/" + id + "." + lang + ".vtt");

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
                String musicUrl = "https://www.youtube.com/watch?v=" + id;
                String directory = System.getProperty("java.io.tmpdir");

                YoutubeDLRequest request = new YoutubeDLRequest(musicUrl, directory);

                request.setOption("ignore-errors");
                request.setOption("extract-audio");
                request.setOption("audio-format", "mp3");
                request.setOption("output", "%(id)s");
                request.setOption("retries", 10);

                YoutubeDL.execute(request);

                Path file = Paths.get(directory + "/" + id + ".mp3");

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
