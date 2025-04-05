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

package cc.wordview.api.service;

import cc.wordview.api.database.entity.VideoLyrics;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.VideoLyricsRepository;
import cc.wordview.api.service.specification.VideoLyricsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoLyricsService implements VideoLyricsServiceInterface {
        @Autowired
        private VideoLyricsRepository repository;

        @Override
        public VideoLyrics getByVideoId(String videoId) throws NoSuchEntryException {
                Optional<VideoLyrics> videoLyrics = repository.findByVideoId(videoId);

                if (!videoLyrics.isPresent()) {
                        throw new NoSuchEntryException("Unable to find any lyrics with this videoId");
                }

                return videoLyrics.get();
        }

        @Override
        public VideoLyrics getById(Long id) {
                throw new UnsupportedOperationException("getById is disabled for VideoLyricsService");
        }

        @Override
        public VideoLyrics insert(VideoLyrics entity) {
                // They should be manually added to the sql
                throw new UnsupportedOperationException("insert is disabled for VideoLyricsService");
        }
}
