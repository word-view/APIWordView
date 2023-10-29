package cc.wordview.api.exception;

public class ValueTakenException extends Exception {
        public ValueTakenException(String message) {
                super("ValueTakenException: " + message);
        }
}
