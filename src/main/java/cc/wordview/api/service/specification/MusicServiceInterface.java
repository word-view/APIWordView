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

package cc.wordview.api.service.specification;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import cc.wordview.api.util.VideoSearchResult;
import com.sapher.youtubedl.YoutubeDLException;

import cc.wordview.ytm.response.LyricEntry;
import cc.wordview.ytm.response.SearchResult;
import cc.wordview.ytm.response.Video;

public interface MusicServiceInterface {
        Video getHistory() throws IOException;

        List<LyricEntry> getSubtitlesList(String id) throws YoutubeDLException;

        String getSubtitle(String id, String lang) throws YoutubeDLException, IOException;

        Path download(String id) throws YoutubeDLException;

        List<VideoSearchResult> search(String query, int maxResults) throws YoutubeDLException;
}
