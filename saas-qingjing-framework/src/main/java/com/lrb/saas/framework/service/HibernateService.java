package com.lrb.saas.framework.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface HibernateService<T> extends BaseService<T>{
	public abstract Session currentSession();

	public abstract T getByProperties(T properties);

	public abstract List<T> findByProperties(T properties);

	public abstract T getByHql(String hql, Map<String, Object> params);

	public abstract List<T> findByHql(String hql, Map<String, Object> params);

	public abstract List<T> findByHql(String hql, Map<String, Object> params, Integer page, Integer rows);

	public abstract <GEN> GEN getGENByHql(String hql, Map<String, Object> params, Class<GEN> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

	public abstract Integer executeHql(String hql, Map<String, Object> params);


}
