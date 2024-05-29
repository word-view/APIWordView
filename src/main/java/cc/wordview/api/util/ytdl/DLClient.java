package cc.wordview.api.util.ytdl;

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
