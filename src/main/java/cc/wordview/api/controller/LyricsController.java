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

package cc.wordview.api.controller;

import cc.wordview.api.Application;
import cc.wordview.api.response.LyricsResponse;
import cc.wordview.api.service.LyricsService;
import cc.wordview.api.util.ArrayUtil;
import cc.wordview.api.util.WordViewResourceResolver;
import cc.wordview.gengolex.Language;
import cc.wordview.gengolex.Parser;
import cc.wordview.gengolex.word.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.ArrayList;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/lyrics")
public class LyricsController extends ServiceController<LyricsService> {
        @Autowired
        private WordViewResourceResolver resourceResolver;

        @GetMapping(produces = "application/json;charset=utf-8")
        public ResponseEntity<?> getLyrics(@RequestParam String id, @RequestParam String lang, @RequestParam String trackName, @RequestParam String artistName) {
                return response(() -> {
                        String decodedTrackName =  URLDecoder.decode(trackName);
                        String decodedArtistName =  URLDecoder.decode(artistName);

                        String lyrics = service.getLyrics(id, decodedTrackName, decodedArtistName, lang)
                                // Remove '\n' so the parser don't have issues with languages that separate words by whitespaces
                                .replace("\n", " ");

                        String dictionariesPath = resourceResolver.getDictionariesPath();

                        Parser parser = new Parser(Language.Companion.byTag(lang), dictionariesPath);

                        ArrayList<Word> words = ArrayUtil.withoutDuplicates(parser.findWords(lyrics));

                        return ok(new LyricsResponse(lyrics, words));
                });
        }
}
