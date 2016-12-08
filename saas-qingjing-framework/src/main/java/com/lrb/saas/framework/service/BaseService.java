package com.lrb.saas.framework.service;

import java.io.Serializable;
import java.util.List;

import com.lrb.saas.core.message.request.query.SAASQueryRequest;

public interface BaseService<T> {

	public abstract void save(T o);

	public abstract void persist(T o);

	public abstract void delete(T o);

	public abstract void saveOrUpdate(T o);

	public abstract void update(T o);

	public abstract void merge(T o);

	public abstract <K> K getById(Class<K> clazz, Serializable id);

	public abstract List<T> query(SAASQueryRequest queryRequest);

}