/*
 * Copyright (c) 2025 Arthur Araujo
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

import cc.wordview.api.exception.*;
import cc.wordview.gengolex.LanguageNotFoundException;
import cc.wordview.wordfind.exception.LyricsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> generic(Exception e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ApiError> unauthorized(Exception e) {
        return error(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<ApiError> badRequest(Exception e) {
        return error(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler({
            NoSuchEntryException.class,
            FileNotFoundException.class,
            LyricsNotFoundException.class,
            LanguageNotFoundException.class,
            ImageNotFoundException.class
    })
    public ResponseEntity<ApiError> notFound(Exception e) {
        return error(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(ValueTakenException.class)
    public ResponseEntity<ApiError> forbidden(Exception e) {
        return error(HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiError> badGateway(Exception e) {
        return error(HttpStatus.BAD_GATEWAY, e);
    }

    private ResponseEntity<ApiError> error(HttpStatus status, Exception e) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(status.value())
                .status(status.name())
                .message(e.getMessage())
                .exceptionName(e.getClass().getSimpleName())
                .build();

        return new ResponseEntity<>(apiError, status);
    }
}
