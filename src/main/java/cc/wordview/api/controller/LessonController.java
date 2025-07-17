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
import cc.wordview.api.request.lesson.KnownWordsRequest;
import cc.wordview.api.request.lesson.PhrasesRequest;
import cc.wordview.api.request.lesson.TranslationsRequest;
import cc.wordview.api.response.PhrasesResponse;
import cc.wordview.api.service.specification.LessonServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.service.util.SimpleTranslation;
import cc.wordview.api.util.ArrayUtil;
import cc.wordview.gengolex.Language;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/lesson")
public class LessonController extends ServiceController<LessonServiceInterface> {
        @Autowired
        private UserServiceInterface userService;

        @PostMapping(path = "/phrase", produces = "application/json;charset=utf-8", consumes = "application/json")
        public ResponseEntity<?> getPhrase(@RequestBody PhrasesRequest request) {
                return response(() -> {
                        request.validate();

                        ArrayList<String> phrases = new ArrayList<>();

                        for (String keyword : request.getKeywords()) {
                                try {
                                        String phrase = service.getPhrase(request.getPhraseLang(), request.getWordsLang(), keyword);
                                        phrases.add(phrase);
                                } catch (NoSuchEntryException ignored) {}
                        }

                        if (phrases.isEmpty())
                                throw new NoSuchEntryException("Couldn't find any phrases matching these keywords");

                        return ok(new PhrasesResponse(phrases));
                });
        }

        @PostMapping(path = "/translations", produces = "application/json;charset=utf-8", consumes = "application/json")
        public ResponseEntity<?> getTranslations(@RequestBody TranslationsRequest request) {
                return response(() -> {
                        request.validate();

                        ArrayList<SimpleTranslation> translations = service.getTranslations(request.getLang(), request.getWords());

                        return ok(translations);
                });
        }

        @GetMapping(path = "/words/known")
        public ResponseEntity<?> getKnownWords(HttpServletRequest request, @RequestParam String lang) {
                return response(() -> {
                        User user = userService.getMe(request);
                        Language language = Language.Companion.byTag(lang);

                        KnownWords knownWords = service.getKnownWords(user.getId(), language.getTag());

                        // the words will be split by ',' in the app itself
                        return ok(knownWords.getWords());
                });
        }

        @PostMapping(path = "/words/known", consumes = "application/json")
        public ResponseEntity<?> addKnownWords(HttpServletRequest req, @RequestBody KnownWordsRequest request) {
                return response(() -> {
                        User user = userService.getMe(req);
                        Language language = Language.Companion.byTag(request.getLanguage());

                        Optional<KnownWords> knownWordsOptional = service.optionalGetKnownWords(user.getId(), language.getTag());

                        KnownWords knownWords = knownWordsOptional.orElseGet(KnownWords::new);

                        knownWords.setUserId(user.getId());
                        knownWords.setLang(language.getTag());

                        List<String> words = new ArrayList<>();

                        if (knownWords.getWords() != null) {
                                words = Arrays.stream(knownWords.getWords().split(",")).toList();
                        }

                        List<String> requestWords = request.getWords();

                        // this seems confusing because the existing `words` array cannot be modified
                        // so here we need to modify the requested words to include the existing ones.
                        // Not a real problem but a detail to note, in the end this inverts the order
                        // of the saved array, this can be seen in the `LessonControllerTest.appendWordsToExistingKnownWords`.
                        requestWords.addAll(words);

                        ArrayList<String> wordsWithoutDuplicates = ArrayUtil.withoutDuplicates(requestWords);

                        knownWords.setWords(String.join(",", wordsWithoutDuplicates));

                        service.insertKnownWords(knownWords);

                        return ok();
                });
        }
}
