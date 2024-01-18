package cc.wordview.api.service;

import static cc.wordview.api.service.ExceptionTemplate.noSuchEntry;

import java.util.ArrayList;
import java.util.Optional;

import cc.wordview.api.exception.NoSuchEntryException;

/**
 * Named Servicer just so it cant be missed for the "@Service" annotation.
 */
public abstract class Servicer {
	public <T, T2> T evaluatePresenceAndReturn(Optional<T> optional, String fieldName, T2 fieldValue)
			throws NoSuchEntryException {

		T optionalValue = optional.orElseThrow(() -> noSuchEntry(fieldName, fieldValue));

		if (optionalValue instanceof ArrayList && ((ArrayList<?>) optionalValue).isEmpty()) {
			throw noSuchEntry(fieldName, fieldValue);
		}

		return optionalValue;
	}
}
