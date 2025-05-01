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

package cc.wordview.api.test.api.request;

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.user.UserCreateRequest;
import cc.wordview.api.request.user.UserLoginRequest;

import static cc.wordview.api.request.ExceptionTemplate.*;

class UserCreateRequestTest extends RequestTest {
	@Test
	void noException() {
		UserLoginRequest request = new UserLoginRequest();

		request.setEmail("arthur@gmail.com");
		request.setPassword("senha23213");

		assertValidationDoesNotThrow(request);
	}

	@Test
	void emailInvalid() throws Exception {
		UserCreateRequest request = new UserCreateRequest();

		request.setUsername("Arthur");
		request.setPassword("senha23213");
		request.setEmail("arthur@cmake.com");

		assertValidationThrows(request, invalid("email"));
	}

	@Test
	void emailEmpty() throws Exception {
		UserCreateRequest request = new UserCreateRequest();

		request.setUsername("Arthur");
		request.setPassword("senha23213");
		request.setEmail("");

		assertValidationThrows(request, emptyOrNull("email"));
	}

	@Test
	void emailNull() throws Exception {
		UserCreateRequest request = new UserCreateRequest();

		request.setUsername("Arthur");
		request.setPassword("senha23213");

		assertValidationThrows(request, emptyOrNull("email"));
	}

	@Test
	void passwordEmpty() throws Exception {
		UserCreateRequest request = new UserCreateRequest();

		request.setUsername("Arthur");
		request.setPassword("");
		request.setEmail("arthur@gmail.com");

		assertValidationThrows(request, emptyOrNull("password"));
	}

	@Test
	void passwordNull() throws Exception {
		UserCreateRequest request = new UserCreateRequest();

		request.setUsername("Arthur");
		request.setEmail("arthur@gmail.com");

		assertValidationThrows(request, emptyOrNull("password"));
	}

}
