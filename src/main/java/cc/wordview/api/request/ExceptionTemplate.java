package cc.wordview.api.request;

import cc.wordview.api.exception.RequestValidationException;

public class ExceptionTemplate {
	public static RequestValidationException emptyOrNull(String field) {
		return new RequestValidationException(field + " cannot be empty or null");
	}

	public static RequestValidationException specialChars(String field) {
		return new RequestValidationException(field + " cannot contain special characters");
	}

	public static RequestValidationException invalid(String field) {
		return new RequestValidationException(field + " is invalid");
	}
}
