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

package cc.wordview.api.service.implementation;

import cc.wordview.api.database.entity.VideoSubtitles;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.repository.VideoSubtitlesRepository;
import cc.wordview.api.service.VideoSubtitlesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoSubtitlesService implements VideoSubtitlesServiceInterface {
    @Autowired
    private VideoSubtitlesRepository repository;


    @Override
    public VideoSubtitles getByVideoId(String videoId) throws NoSuchEntryException {
        Optional<VideoSubtitles> videoSubtitle = repository.findByVideoId(videoId);

        if (!videoSubtitle.isPresent()) {
            throw new NoSuchEntryException("Unable to find any subtitles with this videoId");
        }

        return videoSubtitle.get();
    }

    @Override
    public ArrayList<String> listLyricsIds() {
        Iterable<VideoSubtitles> allSubtitles = repository.findAll();
        ArrayList<String> ids = new ArrayList<>();

        for (VideoSubtitles subtitle : allSubtitles) {
            ids.add(subtitle.getVideoId());
        }

        return ids;
    }

    @Override
    public List<VideoSubtitles> getAll() {
        return (List<VideoSubtitles>) repository.findAll();
    }

    @Override
    public VideoSubtitles getById(Long id) throws NoSuchEntryException {
        throw new UnsupportedOperationException("getById is disabled for VideoSubtitlesService");
    }

    @Override
    public VideoSubtitles insert(VideoSubtitles entity) throws Exception {
        throw new UnsupportedOperationException("insert is disabled for VideoSubtitlesService");
    }
}
