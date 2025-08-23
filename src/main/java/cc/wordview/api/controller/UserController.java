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
import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.exception.ValueTakenException;
import cc.wordview.api.request.user.UserEmailUpdateRequest;
import cc.wordview.api.response.user.NoCredentialsResponse;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.request.user.UserCreateRequest;
import cc.wordview.api.request.user.UserLoginRequest;
import cc.wordview.api.request.user.UserUpdateRequest;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.util.ClassMerger;

import java.security.spec.InvalidKeySpecException;

@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/user")
public class UserController extends ServiceController<UserServiceInterface> {
	@PostMapping(path = "/register", consumes = "application/json")
	public ResponseEntity<?> create(@RequestBody UserCreateRequest request) throws RequestValidationException, ValueTakenException, InvalidKeySpecException {
		String jwtToken = service.register(request.toEntity());
		return created(jwtToken);
	}

	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLoginRequest request) throws RequestValidationException, IncorrectCredentialsException, NoSuchEntryException, InvalidKeySpecException {
		String jwtToken = service.login(request.toEntity());
		return ok(jwtToken);
	}

	@GetMapping("/me")
	public ResponseEntity<?> getMe(HttpServletRequest request) throws NoSuchEntryException {
		return ok(new NoCredentialsResponse(service.getMe(request)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) throws NoSuchEntryException {
		return ok(service.getByIdWithoutCredentials(id));
	}

	@PutMapping("/me")
	public ResponseEntity<?> update(@RequestBody UserUpdateRequest request, HttpServletRequest req) throws Exception {
		User user = service.getMe(req);
		User userAlter = request.toEntity();

		User merged = ClassMerger.merge(user, userAlter);

		service.insert(merged);

		return ok();
	}

	@PutMapping("/me/email")
	public ResponseEntity<?> updateEmail(@RequestBody UserEmailUpdateRequest request, HttpServletRequest req) throws IncorrectCredentialsException, ValueTakenException, NoSuchEntryException, InvalidKeySpecException, RequestValidationException {
		request.validate();

		service.insertWithNewEmail(req, request.getNewEmail(), request.getOldEmail(), request.getPassword());

		return ok();
	}

	@GetMapping("/me/lesson_time")
	public ResponseEntity<?> lessonTime(HttpServletRequest req) throws NoSuchEntryException {
		User user = service.getMe(req);
		return ok(user.getLessonTime());
	}

	@PutMapping("/me/lesson_time")
	public ResponseEntity<?> setLessonTime(HttpServletRequest req, @RequestParam Long time) throws Exception {
		User user = service.getMe(req);

		if (time >= user.getLessonTime()) {
			throw new RequestValidationException("time cannot be bigger or equal to the current time!");
		}

		user.setLessonTime(time);

		service.insert(user);

		return ok();
	}

	@DeleteMapping("/me")
	public ResponseEntity<?> delete(HttpServletRequest request) throws NoSuchEntryException {
		User user = service.getMe(request);
		service.delete(user);
		return ok();
	}
}
