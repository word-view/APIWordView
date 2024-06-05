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

import jakarta.annotation.Nullable;

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

	public static <T> ResponseEntity<?> badGateway(@Nullable T body) {
		return new ResponseEntity<>(body, HttpStatus.BAD_GATEWAY);
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
