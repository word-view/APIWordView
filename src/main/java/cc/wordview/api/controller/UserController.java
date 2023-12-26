package cc.wordview.api.controller;

import cc.wordview.api.Settings;
import cc.wordview.api.controller.response.ExceptionHandler;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.request.user.CreateRequest;
import cc.wordview.api.request.user.DeleteRequest;
import cc.wordview.api.request.user.LoginRequest;
import cc.wordview.api.response.user.CreatedResponse;
import cc.wordview.api.service.specification.UserServiceInterface;

import static cc.wordview.api.controller.response.Response.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = Settings.CORS_ORIGIN)
@RequestMapping(path = Settings.REQUEST_PATH + "/users")
public class UserController {
        @Autowired
        private UserServiceInterface service;

        // CREATE
        @PostMapping(consumes = "application/json")
        public ResponseEntity<?> create(@RequestBody CreateRequest request) {
                return ExceptionHandler.response(() -> {
                        User createdUser = service.insert(request.toEntity());
                        return created(new CreatedResponse(createdUser));
                });
        }

        // READ
        @PostMapping(path = "/login", consumes = "application/json")
        public ResponseEntity<?> login(@RequestBody LoginRequest request) {
                return ExceptionHandler.okResponse(() -> service.login(request.toEntity()));
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> getById(@PathVariable Long id) {
                return ExceptionHandler
                                .okResponse(() -> service.getByIdWithoutCredentials(id));
        }

        @GetMapping
        public ResponseEntity<?> getAll() {
                return ExceptionHandler.okResponse(() -> service.getAllUsers());
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
