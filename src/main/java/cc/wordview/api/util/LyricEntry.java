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

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class LyricEntry {
        private String language;
        private String name;

        public static List<LyricEntry> parse(String input) {
                List<LyricEntry> lyricEntries = new ArrayList<>();
                // Split the input string by lines
                String[] lines = input.split("\n");
                // Start from index 2 to skip the first two lines and iterate till the second
                // last line
                for (int i = 2; i < lines.length - 1; i++) {
                        String line = lines[i];
                        // Split each line by one or more spaces
                        String[] parts = line.split("\\s+");
                        // Extract language and name
                        String language = parts[0];
                        String name = parts[1];
                        LyricEntry entry = new LyricEntry();
                        entry.setLanguage(language);
                        entry.setName(name);
                        lyricEntries.add(entry);
                }
                return lyricEntries;
        }
}
