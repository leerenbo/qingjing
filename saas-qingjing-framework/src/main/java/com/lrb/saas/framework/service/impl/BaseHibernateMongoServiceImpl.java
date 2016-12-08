package com.lrb.saas.framework.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.hibernate.Session;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import com.lrb.saas.core.message.request.query.SAASQueryRequest;
import com.lrb.saas.framework.dao.HibernateDao;
import com.lrb.saas.framework.service.HibernateService;
import com.lrb.saas.framework.service.MongoService;
import com.lrb.saas.framework.util.BeanUtils;

@Service("baseHibernateMongoServiceImpl")
public class BaseHibernateMongoServiceImpl<T> implements MongoService<T>, HibernateService<T> {

	@Resource
	private MongoFindByIdConsumer<T> mongoFindByIdConsumer;

	@Resource(name = "hibernateDaoImpl")
	public HibernateDao<T> hibernateDaoImpl;

	@Resource(name = "mongoTemplate")
	public MongoTemplate mongoTemplate;

	@Override
	public Session currentSession() {
		return hibernateDaoImpl.getCurrentSession();
	}

	@Override
	public void save(T o) {
		if (o == null) {
			return;
		}
		if (o.getClass().isAnnotationPresent(Entity.class)) {
			hibernateDaoImpl.save(o);
		}
		if (o.getClass().isAnnotationPresent(Document.class)) {
			mongoTemplate.insert(o);
		}
	}

	@Override
	public void persist(T o) {
		if (o == null) {
			return;
		}
		if (o.getClass().isAnnotationPresent(Entity.class)) {
			hibernateDaoImpl.persist(o);
		}
		if (o.getClass().isAnnotationPresent(Document.class)) {
			mongoTemplate.insert(o);
		}
	}

	@Override
	public void delete(T o) {
		if (o == null) {
			return;
		}
		if (o.getClass().isAnnotationPresent(Entity.class)) {
			hibernateDaoImpl.delete(o);
		}
		if (o.getClass().isAnnotationPresent(Document.class)) {
			mongoTemplate.remove(o);
		}
	}

	@Override
	public void saveOrUpdate(T o) {
		if (o == null) {
			return;
		}
		if (o.getClass().isAnnotationPresent(Entity.class)) {
			hibernateDaoImpl.saveOrUpdate(o);
		}
		if (o.getClass().isAnnotationPresent(Document.class)) {
			mongoTemplate.save(o);
		}
	}

	@Override
	public void merge(T o) {
		if (o == null) {
			return;
		}
		if (o.getClass().isAnnotationPresent(Entity.class)) {
			hibernateDaoImpl.merge(o);
		}
		if (o.getClass().isAnnotationPresent(Document.class)) {
			mongoTemplate.save(o);
		}
	}

	@Override
	public <K> K getById(Class<K> clazz, Serializable id) {
		if (clazz == null || id == null) {
			return null;
		}
		K re = null;
		K re2 = null;
		if (clazz.isAnnotationPresent(Entity.class)) {
			re = hibernateDaoImpl.getById(clazz, id);
		}
		if (clazz.isAnnotationPresent(Document.class)) {
			re2 = mongoTemplate.findById(id, clazz);
		}
		if (re2 != null) {
			BeanUtils.copyNotNullProperties(re2, re);
		}
		return re;
	}

	@Override
	public T getByProperties(T properties) {
		T re = hibernateDaoImpl.getByProperties(properties);
		mongoFindByIdConsumer.accept(re);
		return re;
	}

	@Override
	public List<T> findByProperties(T properties) {
		List<T> res = hibernateDaoImpl.findByProperties(properties);
		if (properties.getClass().isAnnotationPresent(Document.class)) {
			res.stream().forEach(mongoFindByIdConsumer);
		}
		return res;
	}

	@Override
	public T getByHql(String hql, Map<String, Object> params) {
		T re = hibernateDaoImpl.getByHql(hql, params);
		if (re != null && re.getClass().isAnnotationPresent(Document.class)) {
			mongoFindByIdConsumer.accept(re);
		}
		return re;
	}

	@Override
	public List<T> findByHql(String hql, Map<String, Object> params) {
		List<T> res = hibernateDaoImpl.findByHql(hql, params);
		if (res != null && res.size() > 0 && res.get(0).getClass().isAnnotationPresent(Document.class)) {
			res.stream().forEach(mongoFindByIdConsumer);
		}
		return res;
	}

	@Override
	public List<T> findByHql(String hql, Map<String, Object> params, Integer page, Integer rows) {
		List<T> res = hibernateDaoImpl.findByHql(hql, params, page, rows);
		if (res != null && res.size() > 0 && res.get(0).getClass().isAnnotationPresent(Document.class)) {
			res.stream().forEach(mongoFindByIdConsumer);
		}
		return res;
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
	public void update(T o) {
		if (o == null) {
			return;
		}
		if (o.getClass().isAnnotationPresent(Entity.class)) {
			hibernateDaoImpl.update(o);
		}
		if (o.getClass().isAnnotationPresent(Document.class)) {
			mongoTemplate.save(o);
		}
	}

	@Override
	public List<T> query(SAASQueryRequest queryRequest) {
		List<T> re = hibernateDaoImpl.findByHql(queryRequest.getSkip(), queryRequest.getLimit(), queryRequest.toHql(), queryRequest.toHqlParams());
		re.stream().forEach(mongoFindByIdConsumer);
		return re;
	}
}
