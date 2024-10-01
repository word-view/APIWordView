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

package cc.wordview.api.test.api.controller;

import org.junit.jupiter.api.Test;

import java.net.URLEncoder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LyricsControllerTest extends ControllerTest {
        @Test
        public void getLyrics() throws Exception {
                req.get("/lyrics?id=1cGQotpn8r4&lang=ja&query=%s".formatted(URLEncoder.encode("a")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"));
        }

        @Test
        public void getLyricsWordFind() throws Exception {
                req.get("/lyrics?id=vcw5THyM7Jo&lang=ja&query=%s".formatted(URLEncoder.encode("ツユ 終点の先が在るとするならば。")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"));
        }


        @Test
        public void getLyricsNotFound() throws Exception {
                req.get("/lyrics?id=vcw5THyM7Jo&lang=ja&query=%s".formatted(URLEncoder.encode("a_song_that_probably_doesnt_exist")))
                        .andExpect(status().isNotFound());
        }
}
