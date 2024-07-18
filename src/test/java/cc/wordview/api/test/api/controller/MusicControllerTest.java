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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import cc.wordview.api.Application;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
@ActiveProfiles("test")
class MusicControllerTest {
        @Autowired
        private MockMvc request;

        @Test
        void lyricsList() throws Exception {
                TestRequest.get(request, "/music/lyrics/list?id=sAuEeM_6zpk", status().isOk());
        }

        @Test
        void lyricsListCached() throws Exception {
                TestRequest.get(request, "/music/lyrics/list?id=sAuEeM_6zpk", status().isOk());
                TestRequest.get(request, "/music/lyrics/list?id=sAuEeM_6zpk", status().isOk());
        }

        @Test
        void history() throws Exception {
                TestRequest.get(request, "/music/history", status().isOk());
        }

        @Test
        void lyricsNoCache() throws Exception {
                String directory = System.getProperty("java.io.tmpdir");
                Path file = Paths.get(directory + "/KEg6FXrvHys.ja.vtt");

                if (Files.exists(file))
                        Files.delete(file);

                TestRequest.get(request, "/music/lyrics?id=sAuEeM_6zpk&lang=ja", status().isOk());
        }

        @Test
        void lyrics() throws Exception {
                TestRequest.get(request, "/music/lyrics?id=sAuEeM_6zpk&lang=ja", status().isOk());
                TestRequest.get(request, "/music/lyrics?id=sAuEeM_6zpk&lang=ja", status().isOk());
        }

        @Test
        void lyricsWordFind() throws Exception {
                TestRequest.get(request, "/music/lyrics/find?title=%s".formatted(URLEncoder.encode("tuyu if there was an endpoint")), status().isOk());
        }

        @Test
        void lyricsWordFindFallbackToNetEase() throws Exception {
                TestRequest.get(request, "/music/lyrics/find?title=%s".formatted(URLEncoder.encode("終点の先が在るとするなら")), status().isOk());
        }

        @Test
        void lyricsWordFindNoResults() throws Exception {
                TestRequest.get(request, "/music/lyrics/find?title=%s".formatted("a_song_that_probably_doesnt_exist3311"), status().isNotFound());
        }

        @Test
        void search() throws Exception {
                TestRequest.get(request, "/music/search?q=pandora101", status().isOk());
        }

        @Test
        void downloadNoCache() throws Exception {
                String directory = System.getProperty("java.io.tmpdir");
                Path file = Paths.get(directory + "/KEg6FXrvHys.mp3");

                if (Files.exists(file))
                        Files.delete(file);

                TestRequest.get(request, "/music/download?id=KEg6FXrvHys", status().isOk());
        }

        @Test
        void download() throws Exception {
                TestRequest.get(request, "/music/download?id=KEg6FXrvHys", status().isOk());
                TestRequest.get(request, "/music/download?id=KEg6FXrvHys", status().isOk());
        }
}
