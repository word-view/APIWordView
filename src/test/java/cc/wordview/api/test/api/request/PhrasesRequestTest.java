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

import cc.wordview.api.request.lesson.PhrasesRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;

public class PhrasesRequestTest {
    @Test
    void noException() {
        assertDoesNotThrow(() -> {
            PhrasesRequest request = new PhrasesRequest();

            request.setPhraseLang("en");
            request.setWordsLang("ja");

            ArrayList<String> keywords = new ArrayList<>();
            keywords.add("a");

            request.setKeywords(keywords);

            request.validate();
        });
    }

    @Test
    void keywordsEmpty() throws Exception {
        assertThrows(() -> {
            PhrasesRequest request = new PhrasesRequest();

            request.setPhraseLang("en");
            request.setWordsLang("ja");

            ArrayList<String> keywords = new ArrayList<>();
            request.setKeywords(keywords);

            request.validate();
        }, "Specify at least 1 keyword");
    }

    @Test
    void keywordsEmptyString() throws Exception {
        assertThrows(() -> {
            PhrasesRequest request = new PhrasesRequest();

            request.setPhraseLang("en");
            request.setWordsLang("ja");

            ArrayList<String> keywords = new ArrayList<>();
            keywords.add("");

            request.setKeywords(keywords);

            request.validate();
        }, "One or more of the specified keywords are a empty string");
    }

    @Test
    void keywordsEmptyStringAfterNonEmpty() throws Exception {
        assertThrows(() -> {
            PhrasesRequest request = new PhrasesRequest();

            request.setPhraseLang("en");
            request.setWordsLang("ja");

            ArrayList<String> keywords = new ArrayList<>();
            keywords.add("a");
            keywords.add("b");
            keywords.add("c");
            keywords.add("d");
            keywords.add("");
            keywords.add("e");

            request.setKeywords(keywords);

            request.validate();
        }, "One or more of the specified keywords are a empty string");
    }
}
