package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.Response.created;
import static cc.wordview.api.controller.response.Response.notImplemented;
import static cc.wordview.api.controller.response.ExceptionHandler.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wordview.api.Constants;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.request.user.CreateRequest;
import cc.wordview.api.request.user.DeleteRequest;
import cc.wordview.api.request.user.LoginRequest;
import cc.wordview.api.response.user.CreatedResponse;
import cc.wordview.api.service.specification.UserServiceInterface;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/users")
public class UserController {
	@Autowired
	private UserServiceInterface service;

	// CREATE
	@PostMapping(path = "/register", consumes = "application/json")
	public ResponseEntity<?> create(@RequestBody CreateRequest request) {
		return response(() -> {
			User createdUser = service.insert(request.toEntity());
			return created(new CreatedResponse(createdUser));
		});
	}

	// READ
	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		return okResponse(() -> service.login(request.toEntity()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return okResponse(() -> service.getByIdWithoutCredentials(id));
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		return okResponse(() -> service.getAllUsers());
	}

	@PatchMapping
	public ResponseEntity<?> update(@RequestBody LoginRequest request) {
		return notImplemented();
	}

	// DELETE
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody DeleteRequest request) {
		return notImplemented();
	}
}
