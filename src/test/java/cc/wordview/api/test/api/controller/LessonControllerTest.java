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

public class LessonControllerTest extends ControllerTest {
        @Test
        void getPhrase() throws Exception {
                req.get("/lesson/phrase?phraseLang=en&wordsLang=jp&keyword=%s".formatted(URLEncoder.encode("丘")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrase\":\"He is standing on the hill.\",\"words\":[\"彼\",\"は\",\"丘\",\"の\",\"上\",\"に\",\"立っている\"]}"));

                req.get("/lesson/phrase?phraseLang=pt&wordsLang=jp&keyword=%s".formatted(URLEncoder.encode("丘")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrase\":\"Ele está de pé na montanha.\",\"words\":[\"彼\",\"は\",\"丘\",\"の\",\"上\",\"に\",\"立っている\"]}"));

                req.get("/lesson/phrase?phraseLang=en&wordsLang=pt&keyword=montanha")
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrase\":\"He is standing on the hill.\",\"words\":[\"Ele\",\"está\",\"de\",\"pé\",\"na\",\"montanha\"]}"));

                req.get("/lesson/phrase?phraseLang=pt&wordsLang=en&keyword=hill")
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrase\":\"Ele está de pé na montanha.\",\"words\":[\"He\",\"is\",\"standing\",\"on\",\"the\",\"hill\"]}"));
        }

        @Test
        void getPhraseNonexistentKeyword() throws Exception {
                req.get("/lesson/phrase?phraseLang=pt&wordsLang=en&keyword=aaaaaaaaaaa")
                        .andExpect(status().isNotFound());
        }

        @Test
        void getPhraseNonexistentPhraseLanguage() throws Exception {
                req.get("/lesson/phrase?phraseLang=aaaaa&wordsLang=en&keyword=hill")
                        .andExpect(status().isNotFound());
        }

        @Test
        void getPhraseNonexistentWordsLanguage() throws Exception {
                req.get("/lesson/phrase?phraseLang=pt&wordsLang=aaaaaa&keyword=hill")
                        .andExpect(status().isNotFound());
        }
}
