package br.com.alexegidio.dao;

import java.util.List;

public interface GenericDao<T> {

	void save(T newInstance);

	T load(Long id);

	List<T> listAll();

	void update(T transientObject);

	void delete(T persistentObject);
}
