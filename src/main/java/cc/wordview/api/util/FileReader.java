package cc.wordview.api.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
        public static String read(String fileName) throws IOException {
                byte[] encodedBytes = Files.readAllBytes(Paths.get(fileName));
                return new String(encodedBytes, StandardCharsets.UTF_8);
        }
}
