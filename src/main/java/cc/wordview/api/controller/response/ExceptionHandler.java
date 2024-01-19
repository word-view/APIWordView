package cc.wordview.api.controller.response;

import static cc.wordview.api.controller.response.Response.badRequest;
import static cc.wordview.api.controller.response.Response.forbidden;
import static cc.wordview.api.controller.response.Response.internalServerError;
import static cc.wordview.api.controller.response.Response.notFound;
import static cc.wordview.api.controller.response.Response.unauthorized;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.exception.ValueTakenException;

/**
 * Globally handles exceptions for API responses
 */
public class ExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	public static <T> ResponseEntity<?> okResponse(Callable<T> callable) {
		try {
			return Response.ok(callable.call());
		} catch (IncorrectCredentialsException e) {
			return unauthorized(e.getMessage());
		} catch (RequestValidationException e) {
			return badRequest(e.getMessage());
		} catch (NoSuchEntryException e) {
			return notFound(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return internalServerError(e.getStackTrace());
		}
	}

	public static <T> ResponseEntity<?> createdResponse(Callable<T> callable) {
		try {
			return Response.created(callable.call());
		} catch (IncorrectCredentialsException e) {
			return unauthorized(e.getMessage());
		} catch (RequestValidationException e) {
			return badRequest(e.getMessage());
		} catch (NoSuchEntryException e) {
			return notFound(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return internalServerError(e.getStackTrace());
		}
	}

	/**
	 * In this method the return value is not incapsulated in a existing response
	 */
	public static <T> ResponseEntity<?> response(Callable<T> callable) {
		try {
			return (ResponseEntity<?>) callable.call();
		} catch (IncorrectCredentialsException e) {
			return unauthorized(e.getMessage());
		} catch (RequestValidationException e) {
			return badRequest(e.getMessage());
		} catch (NoSuchEntryException e) {
			return notFound(e.getMessage());
		} catch (ValueTakenException e) {
			return forbidden(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return internalServerError(e.getCause());
		}
	}
}
