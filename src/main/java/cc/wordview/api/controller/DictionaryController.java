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

import cc.wordview.api.Constants;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.request.dictionary.DictionaryRequest;
import cc.wordview.api.response.DictionaryResponse;
import cc.wordview.api.util.ArrayUtil;
import cc.wordview.api.util.WordViewResourceResolver;
import cc.wordview.gengolex.Language;
import cc.wordview.gengolex.Parser;
import cc.wordview.gengolex.word.Word;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/dictionary")
public class DictionaryController {
    @Autowired
    private WordViewResourceResolver resourceResolver;

    @PostMapping(produces = "application/json;charset=utf-8", consumes = "application/json")
    public ResponseEntity<?> getLyrics(@RequestBody DictionaryRequest request) {
        return response(() -> {
            String text = request.getText();

            if (text.isBlank())
                throw new RequestValidationException("Text field can't be blank");

            String dictionariesPath = resourceResolver.getDictionariesPath();

            Parser parser = new Parser(Language.Companion.byTag(request.getLang()), dictionariesPath);

            // Remove '\n' so the parser don't have issues with languages that separate words by whitespaces
            ArrayList<Word> words = ArrayUtil.withoutDuplicates(parser.findWords(text.replace("\n", " ")));

            return ok(new DictionaryResponse(words));
        });
    }
}
