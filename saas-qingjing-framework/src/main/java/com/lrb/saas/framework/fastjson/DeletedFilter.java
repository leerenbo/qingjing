package com.lrb.saas.framework.fastjson;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class DeletedFilter implements PropertyFilter {

	@Override
	public boolean apply(Object object, String name, Object value) {
		if (value==null) {
			return true;
		}
		Field field = FieldUtils.getField(value.getClass(), "sys_deleted", true);
		Boolean deleted = false;
		if (field == null) {
			return true;
		} else {
			try {
				deleted = (Boolean) FieldUtils.readField(field, value,true);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (deleted==null || deleted==false) {
				return true;
			} else {
				return false;
			}
		}
	}

}
