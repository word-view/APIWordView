package cc.wordview.api.service.specification;

import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.ValueTakenException;

public interface ServiceInterface<T1, T2> {
        T1 getById(Long id) throws NoSuchEntryException;

        T2 insert(T1 entity) throws Exception;
}
