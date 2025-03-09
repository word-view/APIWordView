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

package cc.wordview.api.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileHelper {
        public static String read(String fileName) throws IOException {
                byte[] encodedBytes = Files.readAllBytes(Paths.get(fileName));
                return new String(encodedBytes, StandardCharsets.UTF_8);
        }

        public static String read(File file) throws FileNotFoundException {
                StringBuilder fileContent = new StringBuilder();

                Scanner reader = new Scanner(file);

                while (reader.hasNextLine())
                        fileContent.append(reader.nextLine());

                reader.close();

                return fileContent.toString();
        }
}