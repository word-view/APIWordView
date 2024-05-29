package cc.wordview.api.exception;

public class IncorrectCredentialsException extends Exception {
	public IncorrectCredentialsException(String message) {
		super("IncorrectCredentialsException: " + message);
	}
}
