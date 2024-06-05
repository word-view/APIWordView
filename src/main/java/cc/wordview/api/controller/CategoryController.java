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
import static cc.wordview.api.controller.response.ExceptionHandler.*;
import static cc.wordview.api.controller.response.ResponseTemplate.*;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wordview.api.Constants;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.PermissionDeniedException;
import cc.wordview.api.request.category.CategoryCreateRequest;
import cc.wordview.api.service.specification.CategoryServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/category")
public class CategoryController {
	@Autowired
	private CategoryServiceInterface service;

	@Autowired
	private UserServiceInterface userService;

	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> create(@RequestBody CategoryCreateRequest request, HttpServletRequest req) {
		return response(() -> {
			User user = userService.getMe(req);

			if (!user.getRole().equals("ADMIN")) {
				throw new PermissionDeniedException(NOT_ADMIN_MESSAGE);
			}

			service.insert(request.toEntity());
			return created();
		});
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		return okResponse(() -> service.getAll());
	}
}
