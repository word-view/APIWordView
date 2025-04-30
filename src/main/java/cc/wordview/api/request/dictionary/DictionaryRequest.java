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

package cc.wordview.api.request.dictionary;

import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.request.Request;
import cc.wordview.gengolex.Language;
import cc.wordview.gengolex.LanguageNotFoundException;
import lombok.Getter;
import lombok.Setter;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static java.util.Objects.isNull;

@Getter
@Setter
public class DictionaryRequest implements Request {
    private String lang;
    private String text;

    public void validate() throws RequestValidationException {
        try {
            Language.Companion.byTag(lang);
        } catch (LanguageNotFoundException e) {
            throw new RequestValidationException("Specified language was not found");
        }

        if (isNull(text) || text.isEmpty()) {
            throw emptyOrNull("text");
        }
    }
}
