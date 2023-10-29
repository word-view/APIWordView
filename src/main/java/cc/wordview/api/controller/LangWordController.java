package cc.wordview.api.controller;

import cc.wordview.api.Settings;
import cc.wordview.api.controller.response.ExceptionHandler;
import cc.wordview.api.controller.response.ResponseTemplate;
import cc.wordview.api.database.entity.LangWord;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.database.entity.Word;
import cc.wordview.api.request.langword.CreateRequest;
import cc.wordview.api.service.specification.LangWordServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.service.specification.WordServiceInterface;

import static cc.wordview.api.controller.response.Response.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = Settings.CORS_ORIGIN_ALL)
@RequestMapping(path = Settings.REQUEST_PATH + "/langword")
public class LangWordController {
        @Autowired
        private LangWordServiceInterface service;

        @Autowired
        private UserServiceInterface userService;

        @Autowired
        private WordServiceInterface wordService;

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


        @PostMapping("/search")
        private ResponseEntity<?> searchByLessonId(@RequestParam Long lessonid, @RequestParam String lang) {
                return ExceptionHandler.response(() -> {
                        List<Word> words = wordService.getByIdLesson(lessonid);
                        List<LangWord> langWords = new ArrayList<>();

                        for (Word word : words) {
                                langWords.add(service.wordToLangWord(word, lang));
                        }

                        return ok(langWords);
                });
        }
}
