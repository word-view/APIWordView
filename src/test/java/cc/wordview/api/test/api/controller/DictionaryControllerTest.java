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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.MethodName.class)
class DictionaryControllerTest extends ControllerTest {
        @Test
        void getDictionary() throws Exception {
                req.get("/dictionary?lang=kanji")
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"));


                req.get("/dictionary?lang=english")
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"));

                req.get("/dictionary?lang=portuguese")
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=utf-8"));
        }

        @Test
        void getNonexistentDictionary() throws Exception {
                req.get("/dictionary?lang=aaaaaa").andExpect(status().isNotFound());
        }
}
