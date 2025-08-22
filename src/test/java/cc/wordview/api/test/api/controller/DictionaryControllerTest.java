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

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DictionaryControllerTest extends ControllerTest {
    @Test
    void getDictionary() throws Exception {
        req.post("/dictionary", "{\"lang\":\"ja\", \"text\": \"走る, 聞いて\"}")
                .andExpect(status().isOk())
                .andExpect(content().json("{\"dictionary\":[{\"parent\":\"listen\",\"word\":\"聞いて\",\"representable\":false},{\"parent\":\"run\",\"word\":\"走る\",\"representable\":false}]}"));
    }

    @Test
    void getDictionaryInexistentLanguage() throws Exception {
        req.post("/dictionary", "{\"lang\":\"kr\", \"text\": \"走る, 聞いて\"}")
                .andExpect(status().isBadRequest());
    }

    @Test
    void getDictionaryNoText() throws Exception {
        req.post("/dictionary", "{\"lang\":\"ja\", \"text\": \"\"}")
                .andExpect(status().isBadRequest());
    }
}
