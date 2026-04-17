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

package cc.wordview.api.test.api.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class TextTracksControllerTest extends ControllerTest {
    @Test
    @Disabled("Makes a external request, only run this when ABSOLUTELY NECESSARY")
    void getLyrics() throws Exception {
        req.get("/text-tracks/lyrics?id=1cGQotpn8r4&lang=ja&trackName=a&artistName=a")
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=utf-8"));
    }

    @Test
//        @Disabled("Does not make external request but don't seem to work on CI")
    void getLyricsWordView() throws Exception {
        req.get("/text-tracks/lyrics?id=ZnUEeXpxBJ0&lang=pt&trackName=aquarela&artistName=toquinho")
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=utf-8"));
    }

    @Test
    @Disabled("Makes a external request, only run this when ABSOLUTELY NECESSARY")
    void getLyricsWordFind() throws Exception {
        req.get("/text-tracks/lyrics?id=vcw5THyM7Jo&lang=ja&trackName=%s&artistName=%s".formatted(
                        URLEncoder.encode("終点の先が在るとするならば。"),
                        URLEncoder.encode("ツユ")
                ))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=utf-8"));
    }


    @Test
    @Disabled("Makes a external request, only run this when ABSOLUTELY NECESSARY")
    void getLyricsNotFound() throws Exception {
        req.get("/text-tracks/lyrics?id=vcw5THyM7Jo&lang=ja&trackName=%s&artistName=%s".formatted(
                URLEncoder.encode("a_song_that_doesnt_exist"),
                URLEncoder.encode("aa")
        )).andExpect(status().isNotFound());
    }

    @Test
    void getListOfLyricsIds() throws Exception {
        req.get("/text-tracks/list")
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=utf-8"));
    }

    @Test
    @Disabled("Makes a external request, only run this when ABSOLUTELY NECESSARY")
    void getSubtitlesYouTube() throws Exception {
        req.get("/text-tracks/subtitles?id=eCipMjo1vSI&lang=ja")
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=utf-8"));
    }

    @Test
    @Disabled("Disabled until https://github.com/word-view/APIWordView/issues/50 is fixed")
    void getSubtitlesWordView() throws Exception {
        req.get("/text-tracks/subtitles?id=K9Ydi94yT94&lang=ja")
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=utf-8"));
    }
}

