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

package cc.wordview.api.test.api.request;

import cc.wordview.api.request.word.WordCreateRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class WordRequestTest {
	@Test
	void noException() throws Exception {
		assertDoesNotThrow(() -> {
			WordCreateRequest request = new WordCreateRequest();

			request.setName("carro");
			request.setLang("pt-BR");
			request.setTranslatedWord("car");

			request.toEntity();
		});
	}

	@Test
	void nameEmpty() throws Exception {
		assertThrows(() -> {
			WordCreateRequest request = new WordCreateRequest();

			request.setName("");
			request.setLang("pt-BR");
			request.setTranslatedWord("car");

			request.toEntity();
		}, emptyOrNull("name").getMessage());
	}

	@Test
	void nameNull() throws Exception {
		assertThrows(() -> {
			WordCreateRequest request = new WordCreateRequest();

			request.setLang("pt-BR");
			request.setTranslatedWord("car");

			request.toEntity();
		}, emptyOrNull("name").getMessage());
	}

	@Test
	void langEmpty() throws Exception {
		assertThrows(() -> {
			WordCreateRequest request = new WordCreateRequest();

			request.setName("carro");
			request.setLang("");
			request.setTranslatedWord("car");

			request.toEntity();
		}, emptyOrNull("lang").getMessage());
	}

	@Test
	void langNull() throws Exception {
		assertThrows(() -> {
			WordCreateRequest request = new WordCreateRequest();

			request.setName("carro");
			request.setTranslatedWord("car");

			request.toEntity();
		}, emptyOrNull("lang").getMessage());
	}

	@Test
	void translatedWordEmpty() throws Exception {
		assertThrows(() -> {
			WordCreateRequest request = new WordCreateRequest();

			request.setName("carro");
			request.setLang("pt-BR");
			request.setTranslatedWord("");

			request.toEntity();
		}, emptyOrNull("translatedWord").getMessage());
	}

	@Test
	void translatedWordNull() throws Exception {
		assertThrows(() -> {
			WordCreateRequest request = new WordCreateRequest();

			request.setName("carro");
			request.setLang("pt-BR");

			request.toEntity();
		}, emptyOrNull("translatedWord").getMessage());
	}
}
