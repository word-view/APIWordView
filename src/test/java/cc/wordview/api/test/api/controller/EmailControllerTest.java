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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmailControllerTest extends ControllerTest {
        @Test
        void addEmail() throws Exception {
                req.get("/email?email=arthur.araujo@gmail.com").andExpect(status().isOk());
        }

        @Test
        void addExistingEmail() throws Exception {
                req.get("/email?email=mock.user@gmail.com").andExpect(status().isOk());
        }

        @Test
        void invalidEmail() throws Exception {
                req.get("/email?email=notaemail").andExpect(status().isBadRequest());
                req.get("/email?email=notaemai@com.com").andExpect(status().isBadRequest());
                req.get("/email?email=notaemail@").andExpect(status().isBadRequest());
        }
}
