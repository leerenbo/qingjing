package com.lrb.saas.framework.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface SqlService<T> {
	public abstract Object[] getBySql(String sql, Map<String, Object> params);

	public abstract List<Object[]> findBySql(String sql, Map<String, Object> params);

	public abstract List<Object[]> findBySql(String sql, Map<String, Object> params, Integer page, Integer rows);

	public abstract <GEN> List<GEN> findGENBySql(String sql, Map<String, Object> params, Class<GEN> clazz) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException;

	public abstract <GEN> GEN getGENBySql(String sql, Map<String, Object> params, Class<GEN> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

	public abstract Integer executeSql(String sql, Map<String, Object> params);

}
