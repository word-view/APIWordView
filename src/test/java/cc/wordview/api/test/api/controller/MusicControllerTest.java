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

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class MusicControllerTest extends ControllerTest {
        @Test
        void lyricsWordFind() throws Exception {
                req.get("/music/lyrics/find?title=%s".formatted(URLEncoder.encode("tuyu if there was an endpoint")))
                        .andExpect(status().isOk());
        }

        @Test
        void lyricsWordFindFallbackToNetEase() throws Exception {
                req.get("/music/lyrics/find?title=%s".formatted(URLEncoder.encode("終点の先が在るとするなら")))
                        .andExpect(status().isOk());
        }

        @Test
        void lyricsWordFindNoResults() throws Exception {
                req.get("/music/lyrics/find?title=%s".formatted("a_song_that_probably_doesnt_exist3311"))
                        .andExpect(status().isNotFound());
        }
}
