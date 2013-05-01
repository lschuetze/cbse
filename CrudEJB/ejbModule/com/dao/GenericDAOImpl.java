package com.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author Lars Schuetze
 * 
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
	private final static String UNIT_NAME = "CrudPU";

	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;

	private Class<T> entityClass;

	public GenericDAOImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/* (non-Javadoc)
	 * @see com.dao.GenericDAO#save(T)
	 */
	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	/* (non-Javadoc)
	 * @see com.dao.GenericDAO#delete(T)
	 */
	@Override
	public void delete(T entity) {
		T entityToBeRemoved = em.merge(entity);
		em.remove(entityToBeRemoved);
	}

	/* (non-Javadoc)
	 * @see com.dao.GenericDAO#update(T)
	 */
	@Override
	public T update(T entity) {
		return em.merge(entity);
	}

	/* (non-Javadoc)
	 * @see com.dao.GenericDAO#find(int)
	 */
	@Override
	public T find(int entityId) {
		return em.find(entityClass, entityId);
	}

	/* (non-Javadoc)
	 * @see com.dao.GenericDAO#findAll()
	 */
	@Override
	public List<T> findAll() {
		CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;
		try {
			TypedQuery<T> query = em.createNamedQuery(namedQuery, entityClass);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = query.getSingleResult();
		} catch (Exception e) {
			//BAD!!
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	protected void populateQueryParameters(Query query,
			Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
