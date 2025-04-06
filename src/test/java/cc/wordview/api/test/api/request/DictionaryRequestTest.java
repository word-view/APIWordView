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

package cc.wordview.api.test.api.request;

import cc.wordview.api.request.dictionary.DictionaryRequest;
import org.junit.jupiter.api.Test;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static cc.wordview.api.test.api.request.TestException.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DictionaryRequestTest {
    @Test
    void noException() {
        assertDoesNotThrow(() -> {
            DictionaryRequest request = new DictionaryRequest();

            request.setLang("en");
            request.setText("Hello World!");

            request.validate();
        });
    }

    @Test
    void nonExistentLanguage() throws Exception {
        assertThrows(() -> {
            DictionaryRequest request = new DictionaryRequest();

            request.setLang("AAak");
            request.setText("Hello World!");

            request.validate();
        }, "Specified language was not found");
    }

    @Test
    void textEmpty() throws Exception {
        assertThrows(() -> {
            DictionaryRequest request = new DictionaryRequest();

            request.setLang("ja");
            request.setText("");

            request.validate();
        }, emptyOrNull("text").getMessage());
    }

    @Test
    void textNull() throws Exception {
        assertThrows(() -> {
            DictionaryRequest request = new DictionaryRequest();

            request.setLang("ja");

            request.validate();
        }, emptyOrNull("text").getMessage());
    }
}
