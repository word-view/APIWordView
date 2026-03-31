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

package cc.wordview.api.runtime.cache;

import cc.wordview.api.exception.ImageNotFoundException;
import cc.wordview.api.runtime.ResourceResolver;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class ImageCache extends HashMapCacheManager<byte[]> {
    private static final Logger logger = LoggerFactory.getLogger(ImageCache.class);

    @Autowired
    private ResourceResolver resourceResolver;

    @Override
    public void init() throws IOException {
        String imagesPath = resourceResolver.getImagesPath();
        Path filepath = Path.of(imagesPath);

        try (Stream<Path> paths = Files.walk(filepath)) {
            paths.filter(Files::isRegularFile)
                    .forEach(file -> {
                        try (InputStream stream = new FileInputStream(file.toString())) {
                            String[] parts = file.toString().split("/");
                            String imageName = parts[parts.length - 1].replace(".png", "");

                            map.put(imageName, IOUtils.toByteArray(stream));
                        } catch (Exception e) {
                            logger.error("Failed to load image", e);
                        }
                    });

            logger.info("Preloaded {} images", map.size());
        } catch (IOException e) {
            logger.error("Failed to walk through directory: {}", imagesPath, e);
        }
    }

    @SneakyThrows(ImageNotFoundException.class)
    @Override
    public byte[] get(String key) {
        byte[] img = super.get(key);

        if (img == null) {
            throw new ImageNotFoundException("Unable to find a image with this parent");
        } else return img;
    }
}
