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

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.user.UserCreateRequest;

import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UserRequestTest {

	@Test
	void noException() throws Exception {
		assertDoesNotThrow(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("arthur");
			request.setPassword("senha23213");
			request.setEmail("arthur@gmail.com");

			request.toEntity();
		});
	}

	@Test
	void usernameWithSpecialChars() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("asda@!#!@");
			request.setPassword("senha23213");
			request.setEmail("arthur@gmail.com");

			request.toEntity();
		}, specialChars("username").getMessage());
	}

	@Test
	void usernameEmpty() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("");
			request.setPassword("senha23213");
			request.setEmail("arthur@gmail.com");

			request.toEntity();
		}, emptyOrNull("username").getMessage());
	}

	@Test
	void usernameNull() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setPassword("senha23213");
			request.setEmail("arthur@gmail.com");

			request.toEntity();
		}, emptyOrNull("username").getMessage());
	}

	@Test
	void emailInvalid() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setPassword("senha23213");
			request.setEmail("arthur@cmake.com");

			request.toEntity();
		}, invalid("email").getMessage());
	}

	@Test
	void emailEmpty() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setPassword("senha23213");
			request.setEmail("");

			request.toEntity();
		}, emptyOrNull("email").getMessage());
	}

	@Test
	void emailNull() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setPassword("senha23213");

			request.toEntity();
		}, emptyOrNull("email").getMessage());
	}

	@Test
	void passwordEmpty() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setPassword("");
			request.setEmail("arthur@gmail.com");

			request.toEntity();
		}, emptyOrNull("password").getMessage());
	}

	@Test
	void passwordNull() throws Exception {
		assertThrows(() -> {
			UserCreateRequest request = new UserCreateRequest();

			request.setUsername("Arthur");
			request.setEmail("arthur@gmail.com");

			request.toEntity();
		}, emptyOrNull("password").getMessage());
	}
}
