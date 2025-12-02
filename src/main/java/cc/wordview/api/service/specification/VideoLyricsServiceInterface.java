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

package cc.wordview.api.service.specification;

import cc.wordview.api.database.entity.VideoLyrics;
import cc.wordview.api.exception.NoSuchEntryException;

import java.util.ArrayList;

public interface VideoLyricsServiceInterface extends ServiceInterface<VideoLyrics> {
        VideoLyrics getByVideoId(String videoId) throws NoSuchEntryException;
        ArrayList<String> listLyricsIds();
}
