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
import cc.wordview.api.database.entity.KnownWords;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.request.lesson.KnownWordsRequest;
import cc.wordview.api.request.lesson.PhrasesRequest;
import cc.wordview.api.request.lesson.TranslationsRequest;
import cc.wordview.api.response.PhrasesResponse;
import cc.wordview.api.response.TranslationsResponse;
import cc.wordview.api.service.specification.LessonServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.service.util.SimpleTranslation;
import cc.wordview.gengolex.Language;
import cc.wordview.gengolex.LanguageNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/lesson")
public class LessonController extends ServiceController<LessonServiceInterface> {
        @Autowired
        private UserServiceInterface userService;

        @PostMapping(path = "/phrase", produces = "application/json;charset=utf-8", consumes = "application/json")
        public ResponseEntity<?> getPhrase(@RequestBody PhrasesRequest request) throws RequestValidationException, NoSuchEntryException, IOException {
                request.validate();

                String phraseLang = request.getPhraseLang();
                String wordsLang = request.getWordsLang();
                List<String> keywords = request.getKeywords();

                ArrayList<String> phrases = service.getPhrases(phraseLang, wordsLang, keywords);

                return ok(new PhrasesResponse(phrases));
        }

        @PostMapping(path = "/translations", produces = "application/json;charset=utf-8", consumes = "application/json")
        public ResponseEntity<?> getTranslations(@RequestBody TranslationsRequest request) throws RequestValidationException, NoSuchEntryException, IOException {
                request.validate();

                ArrayList<SimpleTranslation> translations = service.getTranslations(request.getLang(), request.getWords());

                return ok(new TranslationsResponse(translations));
        }

        @GetMapping(path = "/words/known")
        public ResponseEntity<?> getKnownWords(HttpServletRequest request, @RequestParam String lang) throws NoSuchEntryException, LanguageNotFoundException {
                User user = userService.getMe(request);
                Language language = Language.Companion.byTag(lang);

                KnownWords knownWords = service.getKnownWords(user.getId(), language.getTag());

                // the words will be split by ',' in the app itself
                return ok(knownWords.getWords());
        }

        @PostMapping(path = "/words/known", consumes = "application/json")
        public ResponseEntity<?> addKnownWords(HttpServletRequest req, @RequestBody KnownWordsRequest request) throws RequestValidationException, NoSuchEntryException, LanguageNotFoundException {
                request.validate();

                User user = userService.getMe(req);
                Language language = Language.Companion.byTag(request.getLanguage());
                List<String> wordsToAdd = request.getWords();

                service.addKnownWords(user, language, wordsToAdd);

                return ok();
        }
}
