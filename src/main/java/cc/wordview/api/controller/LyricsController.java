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
import cc.wordview.api.service.LyricsService;
import cc.wordview.wordfind.LyricsNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/lyrics")
public class LyricsController extends ServiceController<LyricsService> {
        private static final Logger logger = LoggerFactory.getLogger(LyricsController.class);

        @GetMapping
        public ResponseEntity<?> getLyrics(@RequestParam String id, @RequestParam String lang, @RequestParam String query) {
                return response(() -> {
                        String q = URLDecoder.decode(query);

                        String lyrics;

                        try {
                                lyrics = service.getLyrics(id, lang);
                        } catch (LyricsNotFoundException e) {
                                logger.warn("Unable to find any lyrics for '%s' with lang '%s' on youtube.".formatted(id, lang));
                                lyrics = service.getLyricsExternal(q);
                        }

                        return ok(lyrics);
                });
        }
}
