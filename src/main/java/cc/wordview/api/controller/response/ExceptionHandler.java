package cc.wordview.api.controller.response;

import static cc.wordview.api.controller.response.Response.*;

import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.RequestValidationException;

/**
 * Globally handles exceptions for API responses
 */
public class ExceptionHandler {
        public static <T> ResponseEntity<?> okResponse(Callable<T> callable) {
                try {
                        return Response.ok(callable.call());
                }
                catch (IncorrectCredentialsException e) {
                        return unauthorized(e.getMessage());
                }
                catch (RequestValidationException e) {
                        return badRequest(e.getMessage());
                }
                catch (NoSuchEntryException e) {
                        return notFound(e.getMessage());
                }
                catch (Exception e) {
                        return internalServerError(e.getStackTrace());
                }
        }

        public static <T> ResponseEntity<?> createdResponse(Callable<T> callable) {
                try {
                        return Response.created(callable.call());
                }
                catch (IncorrectCredentialsException e) {
                        return unauthorized(e.getMessage());
                }
                catch (RequestValidationException e) {
                        return badRequest(e.getMessage());
                }
                catch (NoSuchEntryException e) {
                        return notFound(e.getMessage());
                }
                catch (Exception e) {
                        return internalServerError(e.getStackTrace());
                }
        }

        /**
         * In this method the return value is not incapsulated in a
         * existing response
         */
        public static <T> ResponseEntity<?> response(Callable<T> callable) {
                try {
                        return (ResponseEntity<?>) callable.call();
                }
                catch (IncorrectCredentialsException e) {
                        return unauthorized(e.getMessage());
                }
                catch (RequestValidationException e) {
                        return badRequest(e.getMessage());
                }
                catch (NoSuchEntryException e) {
                        return notFound(e.getMessage());
                }
                catch (Exception e) {
                        return internalServerError(e.getCause());
                }
        }
}
