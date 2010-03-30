package br.com.alexegidio.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.alexegidio.persistence.HibernateUtil;

public class GenericDaoHibernateImpl<T> implements GenericDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Class<T> domainClass;

	public GenericDaoHibernateImpl(Class<T> type) {
		domainClass = type;
	}

	public Class<T> getDomainClass() {
		return domainClass;
	}

	public void setDomainClass(Class<T> domainClass) {
		this.domainClass = domainClass;
	}

	public void save(T o) {
		getSession().saveOrUpdate(o);
	}

	@SuppressWarnings("unchecked")
	public T load(Class clazz, Integer id) {
		return (T) getSession().get(clazz, id);
	}

	public void update(T o) {
		getSession().update(o);
	}

	public void delete(T o) {
		getSession().delete(o);

	}

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T load(Long id) {
		Criteria criteria = getSession().createCriteria(getDomainClass());
		criteria.add(Restrictions.eq("id", id));
		return (T) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> listByCriteria(Class<T> clazz, Example exampleObject) {

		Criteria criteria = getSession().createCriteria(clazz);
		criteria.add(exampleObject);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public T findByCriteria(Class<T> clazz, Example exampleObject) {

		Criteria criteria = getSession().createCriteria(clazz);
		criteria.add(exampleObject);
		return (T) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll(Class clazz) {
		Criteria criteria = getSession().createCriteria(getDomainClass());
		return criteria.list();

	}
	
	/**
	 * 
	 * @param asc ordenar ascendente ou descendente
	 * @param order propriedade de ordenacao
	 * @param limit o numero maximo de resultados
	 * @return uma List de domainClass
	 */
	@SuppressWarnings("unchecked")
	public List<T> listAll(boolean asc, String order, int limit) {
		
		Criteria criteria = getSession().createCriteria(getDomainClass());
		if (asc) {
			criteria.addOrder(Order.asc(order));
		} else {
			criteria.addOrder(Order.desc(order));
		}
		criteria.setMaxResults(10);
		return criteria.list();
	}

}
