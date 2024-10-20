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
import cc.wordview.api.service.specification.LessonServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/lesson")
public class LessonController extends ServiceController<LessonServiceInterface> {
        @GetMapping(path = "/phrase", produces = "application/json;charset=utf-8")
        public ResponseEntity<?> getPhrase(@RequestParam String phraseLang, @RequestParam String wordsLang, @RequestParam String keyword) {
                return response(() -> {
                        String kword = URLDecoder.decode(keyword);

                        return ok(service.getPhrase(phraseLang, wordsLang, kword));
                });
        }

}
