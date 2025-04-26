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

package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.Response.created;
import static cc.wordview.api.controller.response.Response.ok;

import cc.wordview.api.Application;
import cc.wordview.api.request.user.UserEmailUpdateRequest;
import jakarta.servlet.http.HttpServletRequest;

import static cc.wordview.api.controller.response.ExceptionHandler.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.request.user.UserCreateRequest;
import cc.wordview.api.request.user.UserLoginRequest;
import cc.wordview.api.request.user.UserUpdateRequest;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.util.ClassMerger;

@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/user")
public class UserController extends ServiceController<UserServiceInterface> {
	@PostMapping(path = "/register", consumes = "application/json")
	public ResponseEntity<?> create(@RequestBody UserCreateRequest request) {
		return response(() -> {
			String jwtToken = service.register(request.toEntity());
			return created(jwtToken);
		});
	}

	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
		return response(() -> {
			String jwtToken = service.login(request.toEntity());
			return ok(jwtToken);
		});
	}

	@GetMapping("/me")
	public ResponseEntity<?> getMe(HttpServletRequest request) {
		return response(() -> ok(service.getMe(request)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return response(() -> ok(service.getByIdWithoutCredentials(id)));
	}

	@PutMapping("/me")
	public ResponseEntity<?> update(@RequestBody UserUpdateRequest request, HttpServletRequest req) {
		return response(() -> {
			User user = service.getMe(req);
			User userAlter = request.toEntity();

			User merged = ClassMerger.merge(user, userAlter);

			service.insert(merged);

			return ok();
		});
	}

	@PutMapping("/me/email")
	public ResponseEntity<?> updateEmail(@RequestBody UserEmailUpdateRequest request, HttpServletRequest req) {
		return response(() -> {
			request.validate();

			service.insertWithNewEmail(req, request.getNewEmail(), request.getOldEmail(), request.getPassword());

			return ok();
		});
	}

	@DeleteMapping("/me")
	public ResponseEntity<?> delete(HttpServletRequest request) {
		return response(() -> {
			User user = service.getMe(request);
			service.delete(user);
			return ok();
		});
	}
}
