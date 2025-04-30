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

public class DictionaryRequestTest extends RequestTest {
    @Test
    void noException() {
        DictionaryRequest request = new DictionaryRequest();

        request.setLang("en");
        request.setText("Hello World!");

        assertValidationDoesNotThrow(request);
    }

    @Test
    void nonExistentLanguage() {
        DictionaryRequest request = new DictionaryRequest();

        request.setLang("AAak");
        request.setText("Hello World!");

        assertValidationThrows(request, "Specified language was not found");
    }

    @Test
    void textEmpty() {
        DictionaryRequest request = new DictionaryRequest();

        request.setLang("ja");
        request.setText("");

        assertValidationThrows(request, emptyOrNull("text"));
    }

    @Test
    void textNull() {
        DictionaryRequest request = new DictionaryRequest();

        request.setLang("ja");

        assertValidationThrows(request, emptyOrNull("text"));
    }
}
