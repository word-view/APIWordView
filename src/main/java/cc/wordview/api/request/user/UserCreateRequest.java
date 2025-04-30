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

package cc.wordview.api.request.user;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static cc.wordview.api.request.ExceptionTemplate.invalid;
import static cc.wordview.api.request.ExceptionTemplate.specialChars;
import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.request.RequestValidation;
import lombok.Getter;
import lombok.Setter;
import cc.wordview.api.request.Request;

@Getter
@Setter
public class UserCreateRequest implements Request {
	private String username;
	private String email;
	private String password;

	public User toEntity() throws RequestValidationException {
		this.validate();

		User newUser = new User();

		newUser.setUsername(username);
		newUser.setEmail(email);
		newUser.setPassword(password);

		return newUser;
	}

	public void validate() throws RequestValidationException {
		if (isNull(username) || username.isEmpty()) {
			throw emptyOrNull("username");
		}
		if (RequestValidation.hasSpecialCharacters(username)) {
			throw specialChars("username");
		}

		if (isNull(email) || email.isEmpty()) {
			throw emptyOrNull("email");
		}
		if (RequestValidation.invalidEmail(email)) {
			throw invalid("email");
		}

		if (isNull(password) || password.isEmpty()) {
			throw emptyOrNull("password");
		}

	}
}
