package cc.wordview.api.controller;

import cc.wordview.api.Settings;
import cc.wordview.api.controller.response.ExceptionHandler;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.request.langword.CreateRequest;
import cc.wordview.api.service.specification.LanguageWordServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;

import static cc.wordview.api.controller.response.Response.*;
import static cc.wordview.api.controller.response.ResponseTemplate.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = Settings.CORS_ORIGIN_METRO_DEV)
@RequestMapping(path = Settings.REQUEST_PATH + "/language-word")
public class LanguageWordController {
        @Autowired
        private LanguageWordServiceInterface service;

        @Autowired
        private UserServiceInterface userService;

        @PostMapping(consumes = "application/json")
        public ResponseEntity<?> create(@RequestBody CreateRequest request) {
                return ExceptionHandler.response(() -> {
                        User user = userService.getByToken(request.authorization);

                        if (!user.isAdmin())
                                return notAdmin();

                        service.insert(request.toEntity());
                        return created();
                });
        }
}
