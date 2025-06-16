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
import cc.wordview.api.request.lesson.PhrasesRequest;
import cc.wordview.api.response.PhrasesResponse;
import cc.wordview.api.service.specification.LessonServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.gengolex.Language;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

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
}
