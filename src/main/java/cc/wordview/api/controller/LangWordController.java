package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.Response.created;
import static cc.wordview.api.controller.response.Response.ok;
import static cc.wordview.api.controller.response.ExceptionHandler.*;
import static cc.wordview.api.controller.response.ResponseTemplate.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.wordview.api.Constants;
import cc.wordview.api.controller.response.ExceptionHandler;
import cc.wordview.api.database.entity.LangWord;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.database.entity.Word;
import cc.wordview.api.exception.PermissionDeniedException;
import cc.wordview.api.request.langword.CreateRequest;
import cc.wordview.api.service.specification.LangWordServiceInterface;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.service.specification.WordServiceInterface;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/langword")
public class LangWordController {
	@Autowired
	private LangWordServiceInterface service;

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private WordServiceInterface wordService;

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
