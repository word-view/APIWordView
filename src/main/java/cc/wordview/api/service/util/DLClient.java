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

package cc.wordview.api.service.util;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;

import lombok.Getter;

/**
 * Provides a better API to the ytdl library
 */
public class DLClient {
        @Getter
        private static String defaultDirectory = System.getProperty("java.io.tmpdir");

        public static void downloadSubtitle(String id, String language) throws YoutubeDLException {
                String url = "https://www.youtube.com/watch?v=" + id;

                YoutubeDLRequest request = new YoutubeDLRequest(url, defaultDirectory);

                request.setOption("ignore-errors");
                request.setOption("write-sub");
                request.setOption("sub-lang", language);
                request.setOption("skip-download");
                request.setOption("output", "%(id)s");
                request.setOption("retries", 10);

                YoutubeDL.execute(request);
        }

        public static String listSubtitles(String id) throws YoutubeDLException {
                String url = "https://www.youtube.com/watch?v=" + id;

                YoutubeDLRequest request = new YoutubeDLRequest(url, defaultDirectory);

                request.setOption("ignore-errors");
                request.setOption("list-subs");
                request.setOption("skip-download");
                request.setOption("retries", 10);

                YoutubeDLResponse response = YoutubeDL.execute(request);

                return response.getOut();
        }

        public static void downloadVideo(String id) throws YoutubeDLException {
                String url = "https://www.youtube.com/watch?v=" + id;

                YoutubeDLRequest request = new YoutubeDLRequest(url, defaultDirectory);

                request.setOption("ignore-errors");
                request.setOption("extract-audio");
                request.setOption("audio-quality", 6);
                request.setOption("audio-format", "mp3");
                request.setOption("output", "%(id)s");
                request.setOption("retries", 10);

                YoutubeDL.execute(request);
        }
}