package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.ExceptionHandler.okResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapher.youtubedl.YoutubeDLException;

import cc.wordview.api.Constants;
import cc.wordview.api.service.specification.MusicServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/music")
public class MusicController {
        private static final Logger logger = LoggerFactory.getLogger(MusicController.class);

        @Autowired
        private MusicServiceInterface service;

        @GetMapping("/history")
        public ResponseEntity<?> history() {
                return okResponse(() -> service.getHistory());
        }

        @GetMapping("/lyrics/list")
        public ResponseEntity<?> lyricsList(@RequestParam String id) {
                return okResponse(() -> service.getSubtitlesList(id));
        }

        @GetMapping("/lyrics")
        public ResponseEntity<?> lyrics(@RequestParam String id, @RequestParam String lang,
                        HttpServletResponse response) {
                return okResponse(() -> service.getSubtitle(id, lang));

        }

        @GetMapping("/search")
        public ResponseEntity<?> search(@RequestParam String q) {
                return okResponse(() -> service.search(q, 10));
        }

        @GetMapping("/download")
        public void download(@RequestParam String id, HttpServletResponse response)
                        throws YoutubeDLException, IOException {

                Path file = service.download(id);

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

                logger.info("Started streaming '" + id + "'");
                Files.copy(file, response.getOutputStream());
        }
}
