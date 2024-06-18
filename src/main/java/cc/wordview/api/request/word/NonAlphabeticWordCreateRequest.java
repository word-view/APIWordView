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

package cc.wordview.api.request.word;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.NonAlphabeticWord;
import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonAlphabeticWordCreateRequest {
    private String name;
    private String lang;
    private String translatedWord;
    private String romanizedWord;

    public NonAlphabeticWord toEntity() throws RequestValidationException {
        this.validate();

        NonAlphabeticWord newWord = new NonAlphabeticWord();

        newWord.setName(name);
        newWord.setLang(lang);
        newWord.setTranslatedWord(translatedWord);
        newWord.setRomanizedWord(romanizedWord);

        return newWord;
    }

    private void validate() throws RequestValidationException {

        if (isNull(name) || name.isEmpty()) {
            throw emptyOrNull("name");
        }

        if (isNull(lang) || lang.isEmpty()) {
            throw emptyOrNull("lang");
        }

        if (isNull(translatedWord) || translatedWord.isEmpty()) {
            throw emptyOrNull("translatedWord");
        }

        if (isNull(romanizedWord) || romanizedWord.isEmpty()) {
            throw emptyOrNull("romanizedWord");
        }
    }
}
