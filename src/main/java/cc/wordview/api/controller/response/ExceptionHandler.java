/*
 * Copyright (c) 2024 Arthur Araujo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package cc.wordview.api.controller.response;

import static cc.wordview.api.controller.response.Response.badGateway;
import static cc.wordview.api.controller.response.Response.badRequest;
import static cc.wordview.api.controller.response.Response.forbidden;
import static cc.wordview.api.controller.response.Response.internalServerError;
import static cc.wordview.api.controller.response.Response.notFound;
import static cc.wordview.api.controller.response.Response.unauthorized;

import java.util.concurrent.Callable;

import cc.wordview.wordfind.LyricsNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.PermissionDeniedException;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.exception.ValueTakenException;
import io.jsonwebtoken.io.IOException;

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
		} catch (NoSuchEntryException | LyricsNotFoundException e) {
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
	 * In this method the return value is not encapsulated in an existing response
	 */
	public static <T> ResponseEntity<?> response(Callable<T> callable) {
		try {
			return (ResponseEntity<?>) callable.call();
		} catch (IncorrectCredentialsException | PermissionDeniedException e) {
			return unauthorized(e.getMessage());
		} catch (RequestValidationException e) {
			return badRequest(e.getMessage());
		} catch (NoSuchEntryException e) {
			return notFound(e.getMessage());
		} catch (ValueTakenException e) {
			return forbidden(e.getMessage());
		} catch (IOException e) {
			return badGateway(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return internalServerError(e.getCause());
		}
	}
}
