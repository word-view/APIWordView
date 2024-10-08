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

package cc.wordview.api.controller;

import cc.wordview.api.Constants;
import cc.wordview.api.response.LyricsResponse;
import cc.wordview.api.service.LyricsService;
import cc.wordview.api.util.ArrayUtil;
import cc.wordview.gengolex.Language;
import cc.wordview.gengolex.Parser;
import cc.wordview.gengolex.languages.Word;
import cc.wordview.wordfind.LyricsNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.util.ArrayList;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/lyrics")
public class LyricsController extends ServiceController<LyricsService> {
        private static final Logger logger = LoggerFactory.getLogger(LyricsController.class);

        @Autowired
        private ResourceLoader resourceLoader;

        @GetMapping(produces = "application/json;charset=utf-8")
        public ResponseEntity<?> getLyrics(@RequestParam String id, @RequestParam String lang, @RequestParam String query) {
                return response(() -> {
                        String q = URLDecoder.decode(query);

                        String lyrics;

                        try {
                                String lyricsURL = service.getLyrics(id, lang);

                                RestTemplate restTemplate = new RestTemplate();

                                lyrics = restTemplate.getForObject(lyricsURL, String.class);
                        } catch (LyricsNotFoundException e) {
                                logger.warn("Unable to find any lyrics for '%s' with lang '%s' on youtube.".formatted(id, lang));
                                lyrics = service.getLyricsExternal(q);
                        }

                        String dictionariesPath = resourceLoader.getResource("classpath:/dictionaries").getURI().getPath();

                        Parser parser = new Parser(getLanguageForTag(lang), dictionariesPath);

                        ArrayList<Word> words = ArrayUtil.withoutDuplicates(parser.findWords(lyrics));

                        return ok(new LyricsResponse(lyrics, words));
                });
        }

        private Language getLanguageForTag(String langTag) throws Exception {
                return switch (langTag) {
                        case "ja" -> Language.JAPANESE;
                        case "en" -> Language.ENGLISH;
                        case "pt" -> Language.PORTUGUESE;
                        default -> throw new Exception("No language found for tag '%s'".formatted(langTag));
                };
        }
}
