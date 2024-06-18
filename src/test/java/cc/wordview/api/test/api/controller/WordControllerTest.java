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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import cc.wordview.api.Application;
import cc.wordview.api.test.api.MockValues;
import cc.wordview.api.test.api.controller.mockentity.MockWord;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WordControllerTest {
	@Autowired
	private MockMvc request;

	// CREATE
	@Test
	void create() throws Exception {
		MockWord word = new MockWord("車", "ja", "kuruma");

		String jwt = MockValues.getAdmJwt(request);

		TestRequest.post(request, "/word", word.toJson(), status().isCreated(), jwt);
	}

	@Test
	void createByNonAdmin() throws Exception {
		MockWord word = new MockWord("車", "ja", "kuruma");

		String jwt = MockValues.getUserJwt(request);

		TestRequest.post(request, "/word", word.toJson(), status().isUnauthorized(), jwt);
	}
}
