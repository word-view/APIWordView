package cc.wordview.api.exception;

public class RequestValidationException extends Exception {
	public RequestValidationException(String message) {
		super("RequestValidation: " + message);
	}
}
