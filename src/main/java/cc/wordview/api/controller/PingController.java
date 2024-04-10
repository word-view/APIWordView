package cc.wordview.api.controller;

import static cc.wordview.api.controller.response.ExceptionHandler.okResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wordview.api.Constants;

@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/ping")
public class PingController {
        @GetMapping
        public ResponseEntity<?> ping() {
                return okResponse(() -> "Not dead yet!");
        }
}
