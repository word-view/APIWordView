package cc.wordview.api.controller.resentity;

import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.RequestValidationException;
import static cc.wordview.api.controller.resentity.Response.*;

/**
 * Abstracts away exception handling from the controllers
 */
public class Catcher {
        public static <T> ResponseEntity<?> ok(Callable<T> callable) {
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

        public static <T> ResponseEntity<?> created(Callable<T> callable) {
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
         * Returns the callable instead of wrapping it in a
         * ResponseEntity
         */
        public static <T> ResponseEntity<?> returner(Callable<T> callable) {
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
                        return internalServerError(e.getStackTrace());
                }
        }
}
