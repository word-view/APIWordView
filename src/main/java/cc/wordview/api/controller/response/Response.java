package cc.wordview.api.controller.response;

import javax.annotation.Nullable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
        public static <T> ResponseEntity<?> notFound(@Nullable T body) {
                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        public static ResponseEntity<?> notFound() {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        public static <T> ResponseEntity<?> forbidden(@Nullable T body) {
                return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
        }

        public static ResponseEntity<?> forbidden() {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        public static <T> ResponseEntity<?> unauthorized(@Nullable T body) {
                return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        public static <T> ResponseEntity<?> badRequest(@Nullable T body) {
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        public static <T> ResponseEntity<?> created(@Nullable T body) {
                return new ResponseEntity<>(body, HttpStatus.CREATED);
        }

        public static <T> ResponseEntity<?> ok(@Nullable T body) {
                return new ResponseEntity<>(body, HttpStatus.OK);
        }

        public static <T> ResponseEntity<?> ok() {
                return new ResponseEntity<>(HttpStatus.OK);
        }

        public static ResponseEntity<?> created() {
                return new ResponseEntity<>(HttpStatus.CREATED);
        }

        public static <T> ResponseEntity<?> noContent(@Nullable T body) {
                return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        }

        public static <T> ResponseEntity<?> internalServerError(@Nullable T body) {
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        public static <T> ResponseEntity<?> iAmATeapot(@Nullable T body) {
                return new ResponseEntity<>(body, HttpStatus.I_AM_A_TEAPOT);
        }

        public static <T> ResponseEntity<?> notImplemented() {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
}
