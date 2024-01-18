package cc.wordview.api.service.specification;

import cc.wordview.api.exception.NoSuchEntryException;

public interface ServiceInterface<T1> {
	T1 getById(Long id) throws NoSuchEntryException;

	T1 insert(T1 entity) throws Exception;
}
