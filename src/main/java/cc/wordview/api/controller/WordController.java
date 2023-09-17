package cc.wordview.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cc.wordview.api.Settings;
import cc.wordview.api.controller.resentity.Catcher;
import cc.wordview.api.request.word.CreateRequest;
import cc.wordview.api.service.specification.WordServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.database.entity.User;

import static cc.wordview.api.controller.resentity.Response.*;
import static cc.wordview.api.controller.resentity.ResponseTemplate.*;

@RestController
@RequestMapping(path = Settings.REQUEST_PATH + "/word")
public class WordController {
        @Autowired
        private WordServiceInterface service;

        @Autowired
        private UserServiceInterface userService;

        // CREATE
        @PostMapping(consumes = "application/json")
        public ResponseEntity<?> create(@RequestBody CreateRequest request) {
                return Catcher.returner(() -> {
                        User user = userService.getByToken(request.authorization);

                        if (!user.isAdmin())
                                return notAdmin();

                        service.insert(request.toEntity());
                        return created();
                });
        }

}
