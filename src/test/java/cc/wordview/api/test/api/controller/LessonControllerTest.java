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

import cc.wordview.api.test.api.MockValues;
import cc.wordview.api.test.api.controller.mockentity.MockKnownWordsRequest;
import cc.wordview.api.test.api.controller.mockentity.MockPhraseRequest;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LessonControllerTest extends ControllerTest {
        @Test
        void getSinglePhrase() throws Exception {
                // en -> ja
                req.post("/lesson/phrase", new MockPhraseRequest("en", "ja", keywordsOf("丘")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"He is standing on the hill\\\",\\\"words\\\":[\\\"彼\\\",\\\"は\\\",\\\"丘\\\",\\\"の\\\",\\\"上\\\",\\\"に\\\",\\\"立っている\\\"]}\"]}"));

                req.post("/lesson/phrase", new MockPhraseRequest("en", "ja", keywordsOf("雨")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"It\\\\u0027s raining\\\",\\\"words\\\":[\\\"雨\\\",\\\"は\\\",\\\"降る\\\"]}\"]}"));


                req.post("/lesson/phrase", new MockPhraseRequest("en", "ja", keywordsOf("空")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"The sky is blue\\\",\\\"words\\\":[\\\"空\\\",\\\"は\\\",\\\"青い\\\",\\\"です\\\"]}\"]}"));

                // pt -> ja
                req.post("/lesson/phrase", new MockPhraseRequest("pt", "ja", keywordsOf("丘")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"Ele está de pé na montanha\\\",\\\"words\\\":[\\\"彼\\\",\\\"は\\\",\\\"丘\\\",\\\"の\\\",\\\"上\\\",\\\"に\\\",\\\"立っている\\\"]}\"]}"));

                req.post("/lesson/phrase", new MockPhraseRequest("pt", "ja", keywordsOf("雨")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"Esta chovendo\\\",\\\"words\\\":[\\\"雨\\\",\\\"は\\\",\\\"降る\\\"]}\"]}"));

                req.post("/lesson/phrase", new MockPhraseRequest("pt", "ja", keywordsOf("空")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"O céu e azul\\\",\\\"words\\\":[\\\"空\\\",\\\"は\\\",\\\"青い\\\",\\\"です\\\"]}\"]}"));

                // en -> pt
                req.post("/lesson/phrase", new MockPhraseRequest("en", "pt", keywordsOf("montanha")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"He is standing on the hill\\\",\\\"words\\\":[\\\"Ele\\\",\\\"está\\\",\\\"de\\\",\\\"pé\\\",\\\"na\\\",\\\"montanha\\\"]}\"]}"));

                req.post("/lesson/phrase", new MockPhraseRequest("en", "pt", keywordsOf("chovendo")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"It\\\\u0027s raining\\\",\\\"words\\\":[\\\"Esta\\\",\\\"chovendo\\\"]}\"]}"));

                req.post("/lesson/phrase", new MockPhraseRequest("en", "pt", keywordsOf("céu")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"The sky is blue\\\",\\\"words\\\":[\\\"O\\\",\\\"céu\\\",\\\"e\\\",\\\"azul\\\"]}\"]}"));


                // pt -> en
                req.post("/lesson/phrase", new MockPhraseRequest("pt", "en", keywordsOf("hill")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"Ele está de pé na montanha\\\",\\\"words\\\":[\\\"He\\\",\\\"is\\\",\\\"standing\\\",\\\"on\\\",\\\"the\\\",\\\"hill\\\"]}\"]}"));

                req.post("/lesson/phrase", new MockPhraseRequest("pt", "en", keywordsOf("raining")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"Esta chovendo\\\",\\\"words\\\":[\\\"It\\\\u0027s\\\",\\\"raining\\\"]}\"]}"));

                req.post("/lesson/phrase", new MockPhraseRequest("pt", "en", keywordsOf("sky")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"O céu e azul\\\",\\\"words\\\":[\\\"The\\\",\\\"sky\\\",\\\"is\\\",\\\"blue\\\"]}\"]}"));
        }

        @Test
        void getMultiplePhrases() throws Exception {
                // en -> ja
                req.post("/lesson/phrase", new MockPhraseRequest("en", "ja", keywordsOf("丘", "雨", "空")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"He is standing on the hill\\\",\\\"words\\\":[\\\"彼\\\",\\\"は\\\",\\\"丘\\\",\\\"の\\\",\\\"上\\\",\\\"に\\\",\\\"立っている\\\"]}\",\"{\\\"phrase\\\":\\\"It\\\\u0027s raining\\\",\\\"words\\\":[\\\"雨\\\",\\\"は\\\",\\\"降る\\\"]}\",\"{\\\"phrase\\\":\\\"The sky is blue\\\",\\\"words\\\":[\\\"空\\\",\\\"は\\\",\\\"青い\\\",\\\"です\\\"]}\"]}"));


                // pt -> ja
                req.post("/lesson/phrase", new MockPhraseRequest("pt", "ja", keywordsOf("丘", "雨", "空")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"Ele está de pé na montanha\\\",\\\"words\\\":[\\\"彼\\\",\\\"は\\\",\\\"丘\\\",\\\"の\\\",\\\"上\\\",\\\"に\\\",\\\"立っている\\\"]}\",\"{\\\"phrase\\\":\\\"Esta chovendo\\\",\\\"words\\\":[\\\"雨\\\",\\\"は\\\",\\\"降る\\\"]}\",\"{\\\"phrase\\\":\\\"O céu e azul\\\",\\\"words\\\":[\\\"空\\\",\\\"は\\\",\\\"青い\\\",\\\"です\\\"]}\"]}"));


                // en -> pt
                req.post("/lesson/phrase", new MockPhraseRequest("en", "pt", keywordsOf("montanha", "chovendo", "céu")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"He is standing on the hill\\\",\\\"words\\\":[\\\"Ele\\\",\\\"está\\\",\\\"de\\\",\\\"pé\\\",\\\"na\\\",\\\"montanha\\\"]}\",\"{\\\"phrase\\\":\\\"It\\\\u0027s raining\\\",\\\"words\\\":[\\\"Esta\\\",\\\"chovendo\\\"]}\",\"{\\\"phrase\\\":\\\"The sky is blue\\\",\\\"words\\\":[\\\"O\\\",\\\"céu\\\",\\\"e\\\",\\\"azul\\\"]}\"]}"));


                // pt -> en
                req.post("/lesson/phrase", new MockPhraseRequest("pt", "en", keywordsOf("hill", "raining", "sky")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"Ele está de pé na montanha\\\",\\\"words\\\":[\\\"He\\\",\\\"is\\\",\\\"standing\\\",\\\"on\\\",\\\"the\\\",\\\"hill\\\"]}\",\"{\\\"phrase\\\":\\\"Esta chovendo\\\",\\\"words\\\":[\\\"It\\\\u0027s\\\",\\\"raining\\\"]}\",\"{\\\"phrase\\\":\\\"O céu e azul\\\",\\\"words\\\":[\\\"The\\\",\\\"sky\\\",\\\"is\\\",\\\"blue\\\"]}\"]}"));
        }

        @Test
        void getPhrasesOneMissing() throws Exception {
                // en -> ja
                req.post("/lesson/phrase", new MockPhraseRequest("en", "ja", keywordsOf("丘", "ssssss", "空")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"He is standing on the hill\\\",\\\"words\\\":[\\\"彼\\\",\\\"は\\\",\\\"丘\\\",\\\"の\\\",\\\"上\\\",\\\"に\\\",\\\"立っている\\\"]}\",\"{\\\"phrase\\\":\\\"The sky is blue\\\",\\\"words\\\":[\\\"空\\\",\\\"は\\\",\\\"青い\\\",\\\"です\\\"]}\"]}"));


                // pt -> ja
                req.post("/lesson/phrase", new MockPhraseRequest("pt", "ja", keywordsOf("丘", "aaasdasd", "空")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"Ele está de pé na montanha\\\",\\\"words\\\":[\\\"彼\\\",\\\"は\\\",\\\"丘\\\",\\\"の\\\",\\\"上\\\",\\\"に\\\",\\\"立っている\\\"]}\",\"{\\\"phrase\\\":\\\"O céu e azul\\\",\\\"words\\\":[\\\"空\\\",\\\"は\\\",\\\"青い\\\",\\\"です\\\"]}\"]}"));


                // en -> pt
                req.post("/lesson/phrase", new MockPhraseRequest("en", "pt", keywordsOf("montanha", "chovendo", "dadasdasda")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"He is standing on the hill\\\",\\\"words\\\":[\\\"Ele\\\",\\\"está\\\",\\\"de\\\",\\\"pé\\\",\\\"na\\\",\\\"montanha\\\"]}\",\"{\\\"phrase\\\":\\\"It\\\\u0027s raining\\\",\\\"words\\\":[\\\"Esta\\\",\\\"chovendo\\\"]}\"]}"));


                // pt -> en
                req.post("/lesson/phrase", new MockPhraseRequest("pt", "en", keywordsOf("sadasdasdasdas", "raining", "sky")).toJson())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"))
                        .andExpect(content().json("{\"phrases\":[\"{\\\"phrase\\\":\\\"Esta chovendo\\\",\\\"words\\\":[\\\"It\\\\u0027s\\\",\\\"raining\\\"]}\",\"{\\\"phrase\\\":\\\"O céu e azul\\\",\\\"words\\\":[\\\"The\\\",\\\"sky\\\",\\\"is\\\",\\\"blue\\\"]}\"]}"));
        }

        @Test
        void getPhrasesAllMissing() throws Exception {
                // en -> ja
                req.post("/lesson/phrase", new MockPhraseRequest("en", "ja", keywordsOf("aaaaa", "ssssss", "bbbbb")).toJson())
                        .andExpect(status().isNotFound());

                // pt -> ja
                req.post("/lesson/phrase", new MockPhraseRequest("pt", "ja", keywordsOf("aaaa", "aaasdasd", "bbbbb")).toJson())
                        .andExpect(status().isNotFound());

                // en -> pt
                req.post("/lesson/phrase", new MockPhraseRequest("en", "pt", keywordsOf("aaaa", "sssss", "ddddd")).toJson())
                        .andExpect(status().isNotFound());

                // pt -> en
                req.post("/lesson/phrase", new MockPhraseRequest("pt", "en", keywordsOf("sadasdasdasdas", "ssss", "dddddd")).toJson())
                        .andExpect(status().isNotFound());
        }

        @Test
        void getPhraseNonexistentKeyword() throws Exception {
                req.post("/lesson/phrase", new MockPhraseRequest("pt", "en", keywordsOf("aaaaaaaaaa")).toJson())
                        .andExpect(status().isNotFound());
        }

        @Test
        void getPhraseNonexistentPhraseLanguage() throws Exception {
                req.post("/lesson/phrase", new MockPhraseRequest("aaaaaaaaaaaaa", "en", keywordsOf("hill")).toJson())
                        .andExpect(status().isBadRequest());
        }

        @Test
        void getPhraseNonexistentWordsLanguage() throws Exception {
                req.post("/lesson/phrase", new MockPhraseRequest("en", "aaaaaaaaaa", keywordsOf("hill")).toJson())
                        .andExpect(status().isBadRequest());
        }

        @Test
        void getKnownWords() throws Exception {
                String jwt = MockValues.getUserJwt(mockMvc);

                req.get("/lesson/words/known?lang=en", jwt)
                        .andExpect(status().isOk())
                        .andExpect(content().string("rain,world"));
        }

        @Test
        void getKnownWordsInvalidLang() throws Exception {
                String jwt = MockValues.getUserJwt(mockMvc);

                req.get("/lesson/words/known?lang=www", jwt)
                        .andExpect(status().isNotFound());
        }

        @Test
        void getKnownWordsValidLangButNotKnown() throws Exception {
                String jwt = MockValues.getUserJwt(mockMvc);

                req.get("/lesson/words/known?lang=pt", jwt)
                        .andExpect(status().isNotFound());
        }

        @Test
        void insertNewKnownWords() throws Exception {
                String jwt = MockValues.getUserJwt(mockMvc);

                req.get("/lesson/words/known?lang=ja", jwt)
                        .andExpect(status().isNotFound());

                req.post("/lesson/words/known", new MockKnownWordsRequest("ja", List.of("rain", "world")).toJson(), jwt)
                        .andExpect(status().isOk());

                req.get("/lesson/words/known?lang=ja", jwt)
                        .andExpect(status().isOk())
                        .andExpect(content().string("rain,world"));
        }

        @Test
        void appendWordsToExistingKnownWords() throws Exception {
                String jwt = MockValues.getUserJwt(mockMvc);

                req.get("/lesson/words/known?lang=en", jwt)
                        .andExpect(status().isOk())
                        .andExpect(content().string("rain,world"));

                req.post("/lesson/words/known", new MockKnownWordsRequest("en", List.of("umbrella", "clock")).toJson(), jwt)
                        .andExpect(status().isOk());

                req.get("/lesson/words/known?lang=en", jwt)
                        .andExpect(status().isOk())
                        .andExpect(content().string("umbrella,clock,rain,world"));
        }

        @Test
        void appendRepeatedToKnownWords() throws Exception {
                String jwt = MockValues.getUserJwt(mockMvc);

                req.get("/lesson/words/known?lang=en", jwt)
                        .andExpect(status().isOk())
                        .andExpect(content().string("rain,world"));

                req.post("/lesson/words/known", new MockKnownWordsRequest("en", List.of("rain", "world")).toJson(), jwt)
                        .andExpect(status().isOk());

                req.get("/lesson/words/known?lang=en", jwt)
                        .andExpect(status().isOk())
                        .andExpect(content().string("rain,world"));
        }

        private ArrayList<String> keywordsOf(String... words) {
                return new ArrayList<>(Arrays.asList(words));
        }
}
