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

package cc.wordview.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cc.wordview.wordfind.Lrc2Vtt;
import cc.wordview.wordfind.LyricsNotFoundException;
import cc.wordview.wordfind.LyricsProvider;
import cc.wordview.wordfind.WordFindClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.sapher.youtubedl.YoutubeDLException;
import cc.wordview.api.service.specification.MusicServiceInterface;
import cc.wordview.api.service.util.DLClient;

@Service
public class MusicService implements MusicServiceInterface {
        private static final Logger logger = LoggerFactory.getLogger(MusicService.class);

        @Override
        public String getSubtitleWordFind(String title) throws IOException, LyricsNotFoundException {
                String result;

                try {
                        result = WordFindClient.search(title);
                } catch (LyricsNotFoundException e) {
                        logger.warn("Unable to find any lyrics for \"%s\" in Musixmatch, retrying with NetEase.".formatted(title));
                        // netease is a decent fallback for japanese songs that can't
                        // be found on musixmatch due to their title not being romanised.
                        result = WordFindClient.search(title, LyricsProvider.NETEASE);
                }

                StringBuffer vttLyrics = Lrc2Vtt.convert(result);
                return vttLyrics.toString();
        }

        @Override
        public Path download(String id) throws YoutubeDLException {
                Path file = Paths.get(DLClient.getDefaultDirectory() + "/" + id + ".mp3");

                if (!Files.exists(file))
                        DLClient.downloadVideo(id);

                return file;
        }
}
