package com.lrb.saas.framework.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface HibernateDao<T> {

	public abstract Session getCurrentSession();

	public abstract void save(T o);

	public abstract void persist(T o);

	public abstract void delete(T o);

	public abstract void saveOrUpdate(T o);

	public abstract void update(T o);

	public abstract void merge(T o);

	public abstract <K> K getById(Class<K> clazz, Serializable id);

	public abstract T getByProperties(T properties);

	public abstract List<T> findByProperties(T properties);

	public abstract T getByHql(String hql, Map<String, Object> params);

	public abstract List<T> findByHql(String hql, Map<String, Object> params);

	public abstract List<T> findByHql(String hql, Map<String, Object> params, Integer page, Integer rows);

	public abstract List<?> findObjectByHql(String hql, Map<String, Object> params);

	public abstract List<?> findObjectByHql(String hql, Map<String, Object> params, Integer page, Integer rows);

	public abstract <GEN> GEN getGENByHql(String hql, Map<String, Object> params, Class<GEN> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

	public abstract Integer executeHql(String hql, Map<String, Object> params);

	public abstract Object[] getBySql(String sql, Map<String, Object> params);

	public abstract List<Object[]> findBySql(String sql, Map<String, Object> params);

	public abstract List<Object[]> findBySql(String sql, Map<String, Object> params, Integer page, Integer rows);

	public abstract <GEN> List<GEN> findGENBySql(String sql, Map<String, Object> params, Class<GEN> clazz) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException;

	public abstract <GEN> GEN getGENBySql(String sql, Map<String, Object> params, Class<GEN> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

	public abstract Integer executeSql(String sql, Map<String, Object> params);

	public void callProcedure(String procedureName, LinkedHashMap<String, Object> params);

	List<T> findByHql(Integer skip, Integer limit, String hql, Map<String, Object> params);
}