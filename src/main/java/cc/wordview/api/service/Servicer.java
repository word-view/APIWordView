package cc.wordview.api.service;

import java.util.Optional;

import cc.wordview.api.exception.NoSuchEntryException;
import static cc.wordview.api.service.ExceptionTemplate.*;

/**
 * Named Servicer just so it cant be missed for the "@Service"
 * annotation.
 */
public abstract class Servicer {
        public <T, T2> T evaluatePresenceAndReturn(Optional<T> optional, String fieldName,
                        T2 fieldValue) throws NoSuchEntryException {

                if (!optional.isPresent())
                        throw noSuchEntry(fieldName, fieldValue);

                return optional.get();
        }
}
