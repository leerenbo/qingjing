package com.lrb.saas.framework.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.lrb.saas.framework.dao.HibernateDao;
import com.lrb.saas.framework.util.BeanUtils;

@Repository("hibernateDaoImpl")
public class HibernateDaoImpl<T> implements HibernateDao<T> {

	@Resource
	public SessionFactory sessionFactory;

	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T o) {
		getCurrentSession().save(o);
	}

	@Override
	public void persist(T o) {
		getCurrentSession().persist(o);
	}

	@Override
	public void delete(T o) {
		getCurrentSession().delete(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public void merge(T o) {
		getCurrentSession().merge(o);
	}

	@Override
	public <K> K getById(Class<K> clazz, Serializable id) {
		return (K) getCurrentSession().get(clazz, id);
	}

	/**
	 * 
	 * @see com.datalook.service.base.BaseService#getByProperties(java.lang.Object)
	 * 
	 *      功能描述：根据对象含有的属性查找对象，集合无效 时间：2014年9月12日
	 * @author: lirenbo
	 * @param properties
	 * @return
	 */
	@Override
	public T getByProperties(T properties) {
		List<T> list = findByProperties(properties);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @see com.datalook.dao.base.IBaseDao#findByProperties(T)
	 * 
	 *      功能描述： 时间：2014年9月12日
	 * @author: lirenbo
	 * @param properties
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperties(T properties) {
		if (properties != null) {
			Map<String, Object> m = BeanUtils.toMapWithoutCollection(properties);
			String hql = "from ";
			hql = hql + properties.getClass().getSimpleName() + " pros ";
			if (m.size() > 0) {
				hql = hql + "where ";
				for (String key : m.keySet()) {
					hql = hql + "pros." + key + "=:" + key + " and ";
				}
			}
			hql = hql.substring(0, hql.length() - 5);
			Query<T> q = getCurrentSession().createQuery(hql);
			for (String key : m.keySet()) {
				q.setParameter(key, m.get(key));
			}
			return (List<T>) q.getResultList();
		}
		return null;
	}

	@Override
	public T getByHql(String hql, Map<String, Object> params) {
		List<T> l = findByHql(hql, params);
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<T> findByHql(String hql, Map<String, Object> params) {
		return findByHql(hql, params, null, null);
	}

	@Override
	public List<T> findByHql(Integer skip, Integer limit, String hql, Map<String, Object> params) {
		Query<T> q = getCurrentSession().createQuery(hql);
		setParams(q, params);
		if (skip != null && skip > 0 && limit != null && limit > 0) {
			q.setFirstResult(skip);
			q.setMaxResults(limit);
		}
		return q.getResultList();
	}

	@Override
	public List<T> findByHql(String hql, Map<String, Object> params, Integer page, Integer rows) {
		Query<T> q = getCurrentSession().createQuery(hql);
		setParams(q, params);
		if (page != null && page > 0 && rows != null && rows > 0) {
			q.setFirstResult((page - 1) * rows);
			q.setMaxResults(rows);
		}
		return q.getResultList();
	}

	@Override
	public <GEN> GEN getGENByHql(String hql, Map<String, Object> params, Class<GEN> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Query<?> q = getCurrentSession().createQuery(hql);
		setParams(q, params);
		Object o = q.uniqueResult();
		return (GEN) o;
	}

	@Override
	public Integer executeHql(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		setParams(q, params);
		return q.executeUpdate();
	}

	@Override
	public Object[] getBySql(String sql, Map<String, Object> params) {
		List<Object[]> l = findBySql(sql, params, null, null);
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Object[]> findBySql(String sql, Map<String, Object> params) {
		return findBySql(sql, params, null, null);
	}

	@Override
	public List<Object[]> findBySql(String sql, Map<String, Object> params, Integer page, Integer rows) {
		Query q = getCurrentSession().createSQLQuery(sql);
		setParams(q, params);
		if (page != null && page > 0 && rows != null && rows > 0) {
			q.setFirstResult((page - 1) * rows);
			q.setMaxResults(rows);
		}
		return q.list();
	}

	@Override
	public List<?> findObjectByHql(String hql, Map<String, Object> params) {
		return findObjectByHql(hql, params, null, null);
	}

	@Override
	public List<?> findObjectByHql(String hql, Map<String, Object> params, Integer page, Integer rows) {
		Query q = getCurrentSession().createQuery(hql);
		setParams(q, params);
		if (page != null && page > 0 && rows != null && rows > 0) {
			q.setFirstResult((page - 1) * rows);
			q.setMaxResults(rows);
		}
		return q.list();
	}

	@Override
	public <GEN> List<GEN> findGENBySql(String sql, Map<String, Object> params, Class<GEN> clazz) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException {
		List<GEN> re = new ArrayList<GEN>();
		String[] strs = sql.split("(select +)|( +from)");
		String[] columns = strs[1].split(",");
		Pattern kongge = Pattern.compile(".+\\s.+");
		Pattern dian = Pattern.compile(".+\\..+");
		for (int i = 0; i < columns.length; i++) {
			Matcher konggem = kongge.matcher(columns[i]);
			if (konggem.matches()) {
				columns[i] = columns[i].trim().split(" +")[1];
				continue;
			}
			Matcher dianm = dian.matcher(columns[i]);
			if (dianm.matches()) {
				columns[i] = columns[i].split("\\.")[1].trim();
				continue;
			}
		}
		Query q = getCurrentSession().createSQLQuery(sql);
		setParams(q, params);
		List los = q.list();
		for (Object object : los) {
			GEN c = clazz.newInstance();
			for (int j = 0; j < columns.length; j++) {
				Field f = clazz.getDeclaredField(columns[j]);
				f.setAccessible(true);
				if (columns.length > 1) {
					if (((Object[]) object)[j] instanceof BigInteger) {
						if (f.getType().equals(Long.class)) {
							f.set(c, ((BigInteger) ((Object[]) object)[j]).longValue());
						}
					} else {
						f.set(c, ((Object[]) object)[j]);
					}
				} else if (columns.length == 1) {
					f.set(c, object);
				}
			}
			re.add(c);
		}
		return re;
	}

	@Override
	public <GEN> GEN getGENBySql(String sql, Map<String, Object> params, Class<GEN> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Query q = getCurrentSession().createSQLQuery(sql);
		setParams(q, params);
		Object o = q.uniqueResult();
		return (GEN) o;
	}

	@Override
	public Integer executeSql(String sql, Map<String, Object> params) {
		Query q = getCurrentSession().createSQLQuery(sql);
		setParams(q, params);
		return q.executeUpdate();
	}

	private void setParams(Query q, Map<String, Object> params) {
		String[] paramsNeeded = q.getNamedParameters();
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (ArrayUtils.contains(paramsNeeded, key)) {
					Object o = params.get(key);
					if (o instanceof Collection) {
						q.setParameterList(key, (Collection) o);
					} else if (o instanceof Object[]) {
						q.setParameterList(key, (Object[]) o);
					} else {
						q.setParameter(key, params.get(key));
					}
				}
			}
		}
	}

	public void callProcedure(String procedureName, LinkedHashMap<String, Object> params) {
		Session session = getCurrentSession();
		String str_params_key = "";
		for (String key : params.keySet()) {
			str_params_key += ":" + key + ",";
		}
		str_params_key = str_params_key.substring(0, str_params_key.length() - 1);
		String procedureInfo = "{CALL " + procedureName + "(" + str_params_key + ")}";
		NativeQuery query = session.createNativeQuery(procedureInfo);
		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		query.executeUpdate();
	}

	@Override
	public void update(T o) {
		getCurrentSession().update(o);
	}
}
