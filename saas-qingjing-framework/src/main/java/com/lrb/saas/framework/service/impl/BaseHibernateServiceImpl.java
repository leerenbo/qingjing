package com.lrb.saas.framework.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.lrb.saas.core.message.request.query.SAASQueryRequest;
import com.lrb.saas.framework.dao.HibernateDao;
import com.lrb.saas.framework.service.HibernateService;
import com.lrb.saas.framework.service.SqlService;

@Service("baseHibernateServiceImpl")
public class BaseHibernateServiceImpl<T> implements HibernateService<T>, SqlService<T> {

	@Resource(name = "hibernateDaoImpl")
	public HibernateDao<T> hibernateDaoImpl;

	@Override
	public Session currentSession() {
		return hibernateDaoImpl.getCurrentSession();
	}

	@Override
	public void save(T o) {
		hibernateDaoImpl.save(o);
	}

	@Override
	public void persist(T o) {
		hibernateDaoImpl.persist(o);
	}

	@Override
	public void delete(T o) {
		hibernateDaoImpl.delete(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		hibernateDaoImpl.saveOrUpdate(o);
	}

	@Override
	public void merge(T o) {
		hibernateDaoImpl.merge(o);
	}

	@Override
	public <K> K getById(Class<K> clazz, Serializable id) {
		return hibernateDaoImpl.getById(clazz, id);
	}

	@Override
	public T getByProperties(T properties) {
		return hibernateDaoImpl.getByProperties(properties);
	}

	@Override
	public List<T> findByProperties(T properties) {
		return hibernateDaoImpl.findByProperties(properties);
	}

	@Override
	public T getByHql(String hql, Map<String, Object> params) {
		return hibernateDaoImpl.getByHql(hql, params);
	}

	@Override
	public List<T> findByHql(String hql, Map<String, Object> params) {
		return hibernateDaoImpl.findByHql(hql, params);
	}

	@Override
	public List<T> findByHql(String hql, Map<String, Object> params, Integer page, Integer rows) {
		return hibernateDaoImpl.findByHql(hql, params, page, rows);
	}

	@Override
	public <GEN> GEN getGENByHql(String hql, Map<String, Object> params, Class<GEN> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return hibernateDaoImpl.getGENByHql(hql, params, clazz);
	}

	@Override
	public Integer executeHql(String hql, Map<String, Object> params) {
		return hibernateDaoImpl.executeHql(hql, params);
	}

	@Override
	public Object[] getBySql(String sql, Map<String, Object> params) {
		return hibernateDaoImpl.getBySql(sql, params);
	}

	@Override
	public List<Object[]> findBySql(String sql, Map<String, Object> params) {
		return hibernateDaoImpl.findBySql(sql, params);
	}

	@Override
	public List<Object[]> findBySql(String sql, Map<String, Object> params, Integer page, Integer rows) {
		return hibernateDaoImpl.findBySql(sql, params, page, rows);
	}

	@Override
	public <GEN> List<GEN> findGENBySql(String sql, Map<String, Object> params, Class<GEN> clazz) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException {
		return hibernateDaoImpl.findGENBySql(sql, params, clazz);
	}

	@Override
	public <GEN> GEN getGENBySql(String sql, Map<String, Object> params, Class<GEN> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return hibernateDaoImpl.getGENBySql(sql, params, clazz);
	}

	@Override
	public Integer executeSql(String sql, Map<String, Object> params) {
		return hibernateDaoImpl.executeSql(sql, params);
	}

	@Override
	public void update(T o) {
		hibernateDaoImpl.update(o);
	}

	@Override
	public List<T> query(SAASQueryRequest queryRequest) {
		List<T> re = hibernateDaoImpl.findByHql(queryRequest.getSkip(), queryRequest.getLimit(), queryRequest.toHql(), queryRequest.toHqlParams());
		return re;
	}

}
