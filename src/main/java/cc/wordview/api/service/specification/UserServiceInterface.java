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

package cc.wordview.api.service.specification;

import java.security.spec.InvalidKeySpecException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.ValueTakenException;
import cc.wordview.api.response.user.NoCredentialsResponse;

public interface UserServiceInterface extends ServiceInterface<User> {
	String register(User entity) throws ValueTakenException, InvalidKeySpecException;

	List<NoCredentialsResponse> getAllUsers();

	User getMe(HttpServletRequest request) throws NoSuchEntryException;

	NoCredentialsResponse getByIdWithoutCredentials(Long id) throws NoSuchEntryException;

	User getByEmail(String email) throws NoSuchEntryException;

	String login(User entity) throws NoSuchEntryException, IncorrectCredentialsException, InvalidKeySpecException;

	void delete(User entity) throws NoSuchEntryException, IncorrectCredentialsException;

}
