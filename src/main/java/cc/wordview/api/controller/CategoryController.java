package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.Response.created;
import static cc.wordview.api.controller.response.Response.forbidden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wordview.api.service.specification.CategoryServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.Settings;
import cc.wordview.api.controller.response.ExceptionHandler;
import cc.wordview.api.controller.response.ResponseTemplate;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.request.category.CreateRequest;

@RestController
@CrossOrigin(origins = Settings.CORS_ORIGIN)
@RequestMapping(path = Settings.REQUEST_PATH + "/category")
public class CategoryController {
        @Autowired
        private CategoryServiceInterface service;

        @Autowired
        private UserServiceInterface userService;

        @PostMapping(consumes = "application/json")
        public ResponseEntity<?> create(@RequestBody CreateRequest request) {
                return ExceptionHandler.response(() -> {
                        User user = userService.getByToken(request.authorization);

                        if (!user.isAdmin())
                                return forbidden(ResponseTemplate.NOT_ADMIN_MESSAGE);

                        service.insert(request.toEntity());
                        return created();
                });
        }

        @GetMapping
        public ResponseEntity<?> getAll() {
                return ExceptionHandler.okResponse(() -> service.getAll());
        }
}
