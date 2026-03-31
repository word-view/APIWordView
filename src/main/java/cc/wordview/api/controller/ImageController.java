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
import cc.wordview.api.exception.ImageNotFoundException;
import cc.wordview.api.runtime.ImageCache;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@SuppressWarnings("RedundantThrows")
@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/image")
public class ImageController {
    @Autowired
    private ImageCache cache;

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam String parent) throws ImageNotFoundException {
        return cache.get(parent);
    }

    @PostConstruct
    private void preloadImages() throws IOException {
        cache.init();
    }
}
