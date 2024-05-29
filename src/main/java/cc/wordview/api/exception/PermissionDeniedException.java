package cc.wordview.api.exception;

public class PermissionDeniedException extends Exception {
	public PermissionDeniedException(String message) {
		super("PermissionDeniedException: " + message);
	}
}
