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

package cc.wordview.api.request.lesson;

import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.request.Request;
import cc.wordview.gengolex.Language;
import cc.wordview.gengolex.LanguageNotFoundException;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PhrasesRequest implements Request {
    private String phraseLang;
    private String wordsLang;
    private List<String> keywords;

    public void validate() throws RequestValidationException {
        try {
            Language.Companion.byTag(phraseLang);
        } catch (LanguageNotFoundException e) {
            throw new RequestValidationException("Specified phrase language was not found");
        }

        try {
            Language.Companion.byTag(wordsLang);
        } catch (LanguageNotFoundException e) {
            throw new RequestValidationException("Specified words language was not found");
        }

        if (keywords.isEmpty()) {
            throw new RequestValidationException("Specify at least 1 keyword");
        }

        if (keywords.contains("")) {
            throw new RequestValidationException("One or more of the specified keywords are a empty string");
        }
    }
}
