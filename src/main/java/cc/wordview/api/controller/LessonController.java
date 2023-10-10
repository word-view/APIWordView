package cc.wordview.api.controller;

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

import cc.wordview.api.Settings;
import cc.wordview.api.controller.response.ExceptionHandler;
import cc.wordview.api.controller.response.ResponseTemplate;
import cc.wordview.api.request.lesson.CreateRequest;
import cc.wordview.api.response.lesson.LessonWithWordsResponse;
import cc.wordview.api.service.specification.LessonServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.service.specification.WordServiceInterface;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.database.entity.Lesson;
import cc.wordview.api.database.entity.Word;

import static cc.wordview.api.controller.response.Response.*;
import static java.util.Objects.isNull;

import java.util.List;

@RestController
@CrossOrigin(origins = Settings.CORS_ORIGIN_METRO_DEV)
@RequestMapping(path = Settings.REQUEST_PATH + "/lesson")
public class LessonController {
        @Autowired
        private LessonServiceInterface service;

        @Autowired
        private UserServiceInterface userService;

        @Autowired
        private WordServiceInterface wordService;

        // CREATE
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

        // READ
        @GetMapping("/{id}")
        public ResponseEntity<?> getById(@PathVariable Long id) {
                return ExceptionHandler.response(() -> {
                        Lesson lesson = service.getById(id);
                        List<Word> words = wordService.getByIdLesson(id);

                        return ok(new LessonWithWordsResponse(lesson, words));
                });
        }

        @GetMapping("/search")
        public ResponseEntity<?> search(@RequestParam(required = false) String title,
                        @RequestParam(required = false) String diffi) {

                if (isRequestParamEmpty(title) && isRequestParamEmpty(diffi)) {
                        return badRequest("At least 1 request parameter is necessary");
                }

                if (!isRequestParamEmpty(title))
                        return getByTitle(title);

                if (!isRequestParamEmpty(diffi))
                        return getByDifficulty(diffi);

                return badRequest("Cannot process two request parameters at the same time");
        }

        private ResponseEntity<?> getByTitle(String title) {
                return ExceptionHandler.okResponse(() -> service.getByTitle(title));
        }

        private ResponseEntity<?> getByDifficulty(String diffi) {
                return ExceptionHandler.okResponse(() -> service.getByDifficulty(diffi));
        }

        // UPDATE

        // DELETE

        private boolean isRequestParamEmpty(String param) {
                return isNull(param) || param.isEmpty();
        }
}
