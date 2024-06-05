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

package cc.wordview.api.response.lesson;

import cc.wordview.api.database.entity.Word;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordWithoutId {
        public String name;
        public String imageURL;

        public WordWithoutId(Word word) {
                setName(word.getName());
                setImageURL("https://localhost:8443/img/" + name + ".png");
        }
}
