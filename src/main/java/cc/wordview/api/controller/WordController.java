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

package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.Response.created;
import static cc.wordview.api.controller.response.ResponseTemplate.*;

import cc.wordview.api.database.entity.NonAlphabeticWord;
import cc.wordview.api.request.word.NonAlphabeticWordCreateRequest;
import cc.wordview.api.service.specification.NonAlphabeticWordServiceInterface;
import jakarta.servlet.http.HttpServletRequest;

import static cc.wordview.api.controller.response.ExceptionHandler.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cc.wordview.api.Constants;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.PermissionDeniedException;
import cc.wordview.api.request.word.WordCreateRequest;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.service.specification.WordServiceInterface;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/word")
public class WordController {
	@Autowired
	private WordServiceInterface service;

	@Autowired
	private NonAlphabeticWordServiceInterface nonAlphabeticWordService;

	@Autowired
	private UserServiceInterface userService;

	// CREATE
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> create(@RequestBody WordCreateRequest request, HttpServletRequest req) {
		return response(() -> {
			User user = userService.getMe(req);

			if (!user.getRole().equals("ADMIN")) {
				throw new PermissionDeniedException(NOT_ADMIN_MESSAGE);
			}

			service.insert(request.toEntity());
			return created();
		});
	}

	@PostMapping(value = "/non-alphabetic", consumes = "application/json")
	public ResponseEntity<?> createNonAlphabetic(@RequestBody NonAlphabeticWordCreateRequest request, HttpServletRequest req) {
		return response(() -> {
			User user = userService.getMe(req);

			if (!user.getRole().equals("ADMIN")) {
				throw new PermissionDeniedException(NOT_ADMIN_MESSAGE);
			}

			nonAlphabeticWordService.insert(request.toEntity());
            return created();
        });
	}

	// READ
	@GetMapping
	public ResponseEntity<?> getByName(@RequestParam String name) {
		return okResponse(() -> service.getByName(name));
	}

	@GetMapping(value = "/non-alphabetic")
	public ResponseEntity<?> getByNameNonAlphabetic(@RequestParam String name) {
		return okResponse(() -> nonAlphabeticWordService.getByName(name));
	}
}
