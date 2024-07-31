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

package cc.wordview.api.service;

import cc.wordview.api.service.specification.DictionaryServiceInterface;
import cc.wordview.api.util.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DictionaryService implements DictionaryServiceInterface {
        @Autowired
        private ResourceLoader resourceLoader;

        @Override
        public String getDictionary(String lang) throws IOException {
                Resource dictionaryResource = resourceLoader.getResource("classpath:/dictionaries/%s.json".formatted(lang));
                File dictionaryFile = dictionaryResource.getFile();

                return FileHelper.read(dictionaryFile);
        }
}
