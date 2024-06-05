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

import static cc.wordview.api.controller.response.Response.badRequest;
import static cc.wordview.api.controller.response.Response.created;
import static cc.wordview.api.controller.response.Response.ok;
import static cc.wordview.api.controller.response.ExceptionHandler.*;
import static cc.wordview.api.controller.response.ResponseTemplate.*;
import static java.util.Objects.isNull;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.wordview.api.Constants;
import cc.wordview.api.controller.response.ExceptionHandler;
import cc.wordview.api.database.entity.Lesson;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.PermissionDeniedException;
import cc.wordview.api.request.lesson.LessonCreateRequest;
import cc.wordview.api.response.lesson.LessonWithWordsResponse;
import cc.wordview.api.service.specification.LessonServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.service.specification.WordServiceInterface;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/lesson")
public class LessonController {
	@Autowired
	private LessonServiceInterface service;

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private WordServiceInterface wordService;

	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> create(@RequestBody LessonCreateRequest request, HttpServletRequest req) {
		return response(() -> {
			User user = userService.getMe(req);

			if (!user.getRole().equals("ADMIN")) {
				throw new PermissionDeniedException(NOT_ADMIN_MESSAGE);
			}

			service.insert(request.toEntity());
			return created();
		});
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return response(() -> {
			Lesson lesson = service.getById(id);
			List<Word> words = wordService.getByIdLesson(id);

			return ok(new LessonWithWordsResponse(lesson, words));
		});
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(required = false) String title,
			@RequestParam(required = false) String diffi, @RequestParam(required = false) Long category) {

		if (isRequestParamEmpty(title) && isRequestParamEmpty(diffi) && isRequestParamEmpty(category)) {
			return badRequest("At least 1 request parameter is necessary");
		}

		if (!isRequestParamEmpty(title))
			return getByTitle(title);

		if (!isRequestParamEmpty(diffi))
			return getByDifficulty(diffi);

		if (!isRequestParamEmpty(category))
			return getByCategory(category);

		return badRequest("Cannot process two request parameters at the same time");
	}

	private ResponseEntity<?> getByTitle(String title) {
		return ExceptionHandler.okResponse(() -> service.getByTitle(title));
	}

	private ResponseEntity<?> getByDifficulty(String diffi) {
		return ExceptionHandler.okResponse(() -> service.getByDifficulty(diffi));
	}

	private ResponseEntity<?> getByCategory(Long category) {
		return ExceptionHandler.okResponse(() -> service.getByCategoryId(category));
	}

	private boolean isRequestParamEmpty(String param) {
		return isNull(param) || param.isEmpty();
	}

	private boolean isRequestParamEmpty(Long param) {
		return isNull(param) || param == 0;
	}
}
