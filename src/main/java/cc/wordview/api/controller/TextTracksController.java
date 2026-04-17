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
import cc.wordview.api.response.TextTrackResponse;
import cc.wordview.api.runtime.ResourceResolver;
import cc.wordview.api.service.VideoLyricsServiceInterface;
import cc.wordview.api.service.implementation.TextTracksService;
import cc.wordview.api.util.ArrayUtil;
import cc.wordview.gengolex.Language;
import cc.wordview.gengolex.LanguageNotFoundException;
import cc.wordview.gengolex.Parser;
import cc.wordview.gengolex.word.Word;
import cc.wordview.wordfind.exception.LyricsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/text-tracks")
public class TextTracksController extends ServiceController<TextTracksService> {
    @Autowired
    private ResourceResolver resourceResolver;

    @Autowired
    private VideoLyricsServiceInterface videoLyricsService;

    @GetMapping(produces = "application/json;charset=utf-8", path = "/lyrics")
    public ResponseEntity<?> getLyrics(@RequestParam String id, @RequestParam String lang, @RequestParam String trackName, @RequestParam String artistName) throws IOException, LyricsNotFoundException, LanguageNotFoundException {
        String decodedTrackName = URLDecoder.decode(trackName);
        String decodedArtistName = URLDecoder.decode(artistName);

        String lyrics = service.getLyrics(id, decodedTrackName, decodedArtistName, lang);

        ArrayList<Word> words = getContainingWords(lyrics, lang);

        return ok(new TextTrackResponse(lyrics, words));
    }

    @GetMapping(produces = "application/json;charset=utf-8", path = "/lyrics/list")
    public ResponseEntity<?> getLyricsList() {
        ArrayList<String> ids = videoLyricsService.listLyricsIds();
        return ok(ids);
    }

    @GetMapping(produces = "application/json;charset=utf-8", path = "/subtitles")
    public ResponseEntity<?> getSubtitle(@RequestParam String id, @RequestParam String lang) throws IOException, LanguageNotFoundException {
        String subtitle = service.getSubtitle(id, lang);
        ArrayList<Word> words = getContainingWords(subtitle, lang);

        return ok(new TextTrackResponse(subtitle, words));
    }

    private ArrayList<Word> getContainingWords(String vttFile, String lang) throws IOException, LanguageNotFoundException {
        String dictionariesPath = resourceResolver.getDictionariesPath();

        Parser parser = new Parser(Language.Companion.byTag(lang), dictionariesPath);

        return ArrayUtil.withoutDuplicates(parser.findWords(vttFile.replace("\n", " ")));
    }
}
