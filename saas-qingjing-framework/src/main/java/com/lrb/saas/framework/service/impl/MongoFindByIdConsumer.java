package com.lrb.saas.framework.service.impl;

import java.util.function.Consumer;

import javax.annotation.Resource;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.lrb.saas.framework.util.BeanUtils;

@Component
public class MongoFindByIdConsumer<T> implements Consumer<T> {

	@Resource
	private MongoTemplate mongoTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void accept(T t) {
		T t2 = null;
		if (t == null) {
			return;
		} else if (t.getClass().isAnnotationPresent(org.springframework.data.mongodb.core.mapping.Document.class)) {
			try {
				t2 = (T) mongoTemplate.findById(FieldUtils.readField(t, "id",true), t.getClass());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (t2!=null) {
				BeanUtils.copyNotNullProperties(t2, t);
			}
		}
	}

}
