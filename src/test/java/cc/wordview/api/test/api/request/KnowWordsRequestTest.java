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

import cc.wordview.api.request.lesson.KnownWordsRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class KnowWordsRequestTest extends RequestTest {
    @Test
    void noException() {
        KnownWordsRequest request = new KnownWordsRequest();

        request.setLanguage("pt");

        ArrayList<String> words = new ArrayList<>();
        words.add("a");

        request.setWords(words);

        assertValidationDoesNotThrow(request);
    }

    @Test
    void invalidLang() {
        KnownWordsRequest request = new KnownWordsRequest();

        request.setLanguage("qweqweqwe");

        ArrayList<String> words = new ArrayList<>();
        words.add("a");

        request.setWords(words);

        assertValidationThrows(request, "Specified language was not found");
    }

    @Test
    void emptyWords() {
        KnownWordsRequest request = new KnownWordsRequest();

        request.setLanguage("pt");
        request.setWords(new ArrayList<>());

        assertValidationThrows(request, "Specify at least 1 keyword");
    }

    @Test
    void emptyStringInWords() {
        KnownWordsRequest request = new KnownWordsRequest();

        request.setLanguage("pt");

        ArrayList<String> words = new ArrayList<>();
        words.add("");

        request.setWords(words);

        assertValidationThrows(request, "One or more of the specified words are a empty string");
    }
}
