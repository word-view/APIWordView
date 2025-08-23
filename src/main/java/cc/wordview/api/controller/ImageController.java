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
import cc.wordview.api.util.WordViewResourceResolver;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/image")
public class ImageController {
        private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

        @Autowired
        private WordViewResourceResolver resourceResolver;

        private final Map<String, byte[]> images = new HashMap<>();

        @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
        public @ResponseBody byte[] getImage(@RequestParam String parent) throws ImageNotFoundException {
                byte[] image = images.get(parent);

                if (image == null) {
                        throw new ImageNotFoundException("Unable to find a image with this parent");
                } else return image;
        }

        @PostConstruct
        private void preloadImages() throws IOException {
                String imagesPath = resourceResolver.getImagesPath();

                try (Stream<Path> paths = Files.walk(Path.of(imagesPath))) {
                        paths.filter(Files::isRegularFile)
                                .forEach(file -> {
                                        try (InputStream stream = new FileInputStream(file.toString())) {
                                                String[] parts = file.toString().split("/");
                                                String imageName = parts[parts.length - 1].replace(".png", "");

                                                logger.info("Loading image \"{}.png\"", imageName);

                                                images.put(imageName, IOUtils.toByteArray(stream));
                                        } catch (Exception e) {
                                                logger.error("Failed to load image", e);
                                        }
                                });
                } catch (IOException e) {
                    logger.error("Failed to walk through directory: {}", imagesPath, e);
                }
        }
}
