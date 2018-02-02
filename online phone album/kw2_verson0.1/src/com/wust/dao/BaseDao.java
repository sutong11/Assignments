package com.wust.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {
	@Resource
	public HibernateTemplate hibernateTemplate;

	/**
	 * saveObject
	 * 
	 * @param ectity
	 */
	public void saveObject(Object ectity) {
		this.hibernateTemplate.save(ectity);
	}

	/**
	 * findListByHql
	 * 
	 * @param hql
	 * @param values
	 * @return List
	 */
	public List findListByHql(String hql, Object[] values) {
		return this.hibernateTemplate.find(hql, values);
	}

	/**
	 * findObjByFenye
	 * 
	 * @param hql
	 * @param offset
	 * @param line
	 * @return
	 */
	public List findObjByFenye(final String hql, final int offset,
			final int line) {
		return this.hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(line);
				return query.list();
			}
		});
	}

	/**
	 * singerResult
	 * 
	 * @param hql
	 * @return
	 */
	public Object singerResult(final String hql) {
		return this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.uniqueResult();
			}
		});
	}

	/**
	 * deleteObject
	 * 
	 * @param entityClass
	 * @param id
	 */
	public void deleteObject(Class entityClass, Serializable id)
			throws Exception {
		this.hibernateTemplate.delete(this.hibernateTemplate.load(entityClass,
				id));
	}

	/**
	 * getObjectById
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object getObjectById(Class entityClass, Serializable id) {
		return this.hibernateTemplate.get(entityClass, id);
	}

	/**
	 * updateObject
	 * 
	 * @param entity
	 */
	public void updateObject(Object entity) {
		this.hibernateTemplate.update(entity);
	}

	/**
	 * 
	 * @param hql
	 * @param offset
	 * @param line
	 * @return
	 */
	public List check(final String hql) {
		return this.hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
	}
}
