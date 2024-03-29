package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.Response.created;
import static cc.wordview.api.controller.response.ResponseTemplate.*;

import javax.servlet.http.HttpServletRequest;

import static cc.wordview.api.controller.response.ExceptionHandler.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
