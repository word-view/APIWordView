package cc.wordview.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wordview.api.Constants;
import cc.wordview.ytm.YoutubeApi;
import cc.wordview.ytm.response.SearchResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/music")
public class MusicController {
        @Value("${wordview.ytm.api-key}")
        private String API_KEY;

        private YoutubeApi api = new YoutubeApi();

        @GetMapping("/search")
        public List<SearchResult> search(@RequestParam String q) throws IOException {
                List<SearchResult> results = api.search(q, 10, API_KEY);
                return results;
        }
}
