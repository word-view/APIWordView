package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.Response.created;
import static cc.wordview.api.controller.response.Response.ok;

import jakarta.servlet.http.HttpServletRequest;

import static cc.wordview.api.controller.response.ExceptionHandler.*;

import org.springframework.beans.factory.annotation.Autowired;
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

import cc.wordview.api.Constants;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.request.user.UserCreateRequest;
import cc.wordview.api.request.user.UserLoginRequest;
import cc.wordview.api.request.user.UserUpdateRequest;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.util.ClassMerger;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/user")
public class UserController {
	@Autowired
	private UserServiceInterface service;

	// CREATE
	@PostMapping(path = "/register", consumes = "application/json")
	public ResponseEntity<?> create(@RequestBody UserCreateRequest request) {
		return response(() -> {
			String jwtToken = service.register(request.toEntity());
			return created(jwtToken);
		});
	}

	// READ
	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
		return response(() -> {
			String jwtToken = service.login(request.toEntity());
			return ok(jwtToken);
		});
	}

	@GetMapping("/me")
	public ResponseEntity<?> getMe(HttpServletRequest request) {
		return okResponse(() -> service.getMe(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return okResponse(() -> service.getByIdWithoutCredentials(id));
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

	// DELETE
	@DeleteMapping("/me")
	public ResponseEntity<?> delete(HttpServletRequest request) {
		return response(() -> {
			User user = service.getMe(request);
			service.delete(user);
			return ok();
		});
	}
}
