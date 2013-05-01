package com.dao;

import java.util.List;

/**
 * 
 * @author Lars Schuetze
 *
 * @param <T> the generic class type
 */
public interface GenericDAO<T> {

	public abstract void save(T entity);

	public abstract void delete(T entity);

	public abstract T update(T entity);

	public abstract T find(int entityId);

	public abstract List<T> findAll();

}