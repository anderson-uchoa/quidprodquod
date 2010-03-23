package br.com.alexegidio.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import br.com.alexegidio.model.Role;
import br.com.alexegidio.persistence.HibernateUtil;

public class GenericDaoHibernateImpl<T> implements GenericDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Class<T> domainClass;

	public GenericDaoHibernateImpl() {
		// domainClass = type;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getDomainClass() {
		this.domainClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return domainClass;
	}

	public void setDomainClass(Class<T> domainClass) {
		this.domainClass = domainClass;
	}

	public void save(T o) {
		HibernateUtil.getSessionFactory().getCurrentSession().save(o);
	}

	@SuppressWarnings("unchecked")
	public T load(Class clazz, Integer id) {
		return (T) HibernateUtil.getSessionFactory().getCurrentSession().get(
				clazz, id);
	}

	public void update(T o) {
		getSession().update(o);
	}

	public void delete(T o) {
		HibernateUtil.getSessionFactory().getCurrentSession().delete(o);

	}

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T load(Long id) {
		Criteria criteria = HibernateUtil.getSessionFactory()
				.getCurrentSession().createCriteria(getDomainClass());
		criteria.add(Restrictions.eq("id", id));
		// return (T) getSession().load(getDomainClass(), id);
		return (T) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> listByCriteria(Class<T> clazz, Example exampleObject) {

		Criteria criteria = HibernateUtil.getSessionFactory()
				.getCurrentSession().createCriteria(clazz);
		criteria.add(exampleObject);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public T findByCriteria(Class<T> clazz, Example exampleObject) {

		Criteria criteria = HibernateUtil.getSessionFactory()
				.getCurrentSession().createCriteria(clazz);
		criteria.add(exampleObject);
		return (T) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll(Class clazz) {
		// Criteria criteria = getSession().createCriteria(getDomainClass());
		Criteria criteria = HibernateUtil.getSessionFactory()
				.getCurrentSession().createCriteria(Role.class);
		return criteria.list();

	}

}
