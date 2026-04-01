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
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.runtime.ResourceResolver;
import cc.wordview.api.runtime.cache.FeedCache;
import cc.wordview.gengolex.Language;
import cc.wordview.gengolex.LanguageNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/home")
public class HomeController {
    @Autowired
    private FeedCache cache;

    @GetMapping(produces = "application/json;charset=utf-8")
    public ResponseEntity<?> getHome(@RequestParam String learnLang) throws LanguageNotFoundException, NoSuchEntryException {
        // Make gengolex itself check if the tag is valid
        Language.Companion.byTag(learnLang);
        var home = cache.get(learnLang);

        if (home == null) {
            throw new NoSuchEntryException("Unable to find a feed for this language");
        }

        return ok(home);
    }

    @PostConstruct
    private void initializeFeeds() throws IOException {
        cache.init();
    }
}