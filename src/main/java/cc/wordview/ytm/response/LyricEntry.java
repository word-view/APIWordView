package cc.wordview.ytm.response;

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
