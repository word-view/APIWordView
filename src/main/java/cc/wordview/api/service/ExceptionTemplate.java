package cc.wordview.api.service;

import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.ValueTakenException;

public class ExceptionTemplate {
	public static <T> NoSuchEntryException noSuchEntry(String entryName, T entryValue) {
		return new NoSuchEntryException("No entries found with " + entryName + ": " + entryValue);
	}

	public static ValueTakenException valueTaken(String entryName) {
		return new ValueTakenException(entryName + " is already taken");
	}

	public static <T> IncorrectCredentialsException incorrectCredentials() {
		return new IncorrectCredentialsException("Credentials did not match");
	}
}
