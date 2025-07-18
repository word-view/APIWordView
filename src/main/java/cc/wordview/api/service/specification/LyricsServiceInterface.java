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

import cc.wordview.wordfind.exception.LyricsNotFoundException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;

import java.io.IOException;

public interface LyricsServiceInterface {
        /**
         * Retrieves the lyrics for a track
         *
         * @param id the video ID associated with the track (e.g., YouTube video ID).
         * @param trackName the name of the track (used in external fallback).
         * @param artistName the name of the artist (used in external fallback).
         * @param langTag the language tag to match subtitle language (e.g., "en", "ja").
         * @return the lyrics as a subtitle-formatted (VTT) string.
         * @throws ExtractionException if subtitle extraction from YouTube fails.
         * @throws IOException if reading lyrics from disk or network fails.
         * @throws LyricsNotFoundException if no valid lyrics are found.
         */
        String getLyrics(String id, String trackName, String artistName, String langTag) throws ExtractionException, IOException, LyricsNotFoundException;

        /**
         * Attempts to retrieve lyrics for the given track and artist from an external provider from WordFind.
         *
         * @param trackName the name of the track to search lyrics for.
         * @param artistName the name of the artist who performs the track.
         * @return the lyrics as a subtitle-formatted (VTT) string.
         * @throws IOException if a network or I/O error occurs during lookup.
         * @throws LyricsNotFoundException if no lyrics are found for the provided track and artist.
         */
        String getLyricsExternal(String trackName, String artistName) throws IOException, LyricsNotFoundException;
}

