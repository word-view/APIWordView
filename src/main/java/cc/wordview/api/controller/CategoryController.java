package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.Response.created;
import static cc.wordview.api.controller.response.ExceptionHandler.*;
import static cc.wordview.api.controller.response.ResponseTemplate.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wordview.api.Constants;
import cc.wordview.api.controller.response.ExceptionHandler;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.PermissionDeniedException;
import cc.wordview.api.request.category.CreateRequest;
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
	public ResponseEntity<?> create(@RequestBody CreateRequest request, HttpServletRequest req) {
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
		return ExceptionHandler.okResponse(() -> service.getAll());
	}
}
