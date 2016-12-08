package com.lrb.saas.framework.fastjson;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.collection.spi.PersistentCollection;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class ResponseHibernateFilter implements PropertyFilter {
	private final Set<String> includes = new HashSet<String>();
	private final Set<String> excludes = new HashSet<String>();

	public ResponseHibernateFilter() {
	}

	public Set<String> getIncludes() {
		return includes;
	}

	public Set<String> getExcludes() {
		return excludes;
	}

	@Override
	public boolean apply(Object source, String name, Object value) {
		if (includes.contains(name)) {
			return true;
		}
/*		if (value != null && (value instanceof PersistentCollection)) {// 避免hibernate对象循环引用，一切hibernate集合不予序列化
			return false;
		}
*/		if (excludes.contains(name)) {
			return false;
		}
		if (excludes.contains(source.getClass().getSimpleName() + "." + name)) {
			return false;
		}
		if (includes.size() == 0) {
			return true;
		}
		if (includes.contains(source.getClass().getSimpleName() + "." + name)) {
			return true;
		}
		return false;
	}

}
