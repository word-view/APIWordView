package cc.wordview.api.exception;

public class NoSuchEntryException extends Exception {
        public NoSuchEntryException(String message) { super("NoSuchEntry: " + message); }
}
