package com.lrb.saas.framework.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import com.lrb.saas.core.message.request.query.SAASQueryRequest;
import com.lrb.saas.framework.service.MongoService;

@Service("baseMongoServiceImpl")
public class BaseMongoServiceImpl<T> implements MongoService<T> {

	@Resource(name = "mongoTemplate")
	public MongoTemplate mongoTemplate;

	@Override
	public void save(T o) {
		if (o == null) {
			return;
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
		if (o.getClass().isAnnotationPresent(Document.class)) {
			mongoTemplate.insert(o);
		}
	}

	@Override
	public void delete(T o) {
		if (o == null) {
			return;
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
		if (o.getClass().isAnnotationPresent(Document.class)) {
			mongoTemplate.save(o);
		}
	}

	@Override
	public void merge(T o) {
		if (o == null) {
			return;
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
		if (clazz.isAnnotationPresent(Document.class)) {
			re = mongoTemplate.findById(id, clazz);
		}
		return re;
	}

	@Override
	public void update(T o) {
		mongoTemplate.save(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(SAASQueryRequest queryRequest) {
		return mongoTemplate.find(queryRequest.toMongoQuery(), queryRequest.getClazz());
	}

}
