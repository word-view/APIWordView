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

package cc.wordview.api.util;

public class Json {
        public static String minifyJson(String json) {
                StringBuilder result = new StringBuilder(json.length());
                boolean inQuotes = false;
                boolean escapeMode = false;
                for (char character : json.toCharArray()) {
                        if (escapeMode) {
                                result.append(character);
                                escapeMode = false;
                        } else if (character == '"') {
                                inQuotes = !inQuotes;
                                result.append(character);
                        } else if (character == '\\') {
                                escapeMode = true;
                                result.append(character);
                        } else if (!inQuotes && character == ' ') {
                                continue;
                        } else {
                                result.append(character);
                        }
                }
                return result.toString();
        }
}
