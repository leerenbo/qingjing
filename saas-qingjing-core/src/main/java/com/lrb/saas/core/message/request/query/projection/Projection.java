package com.lrb.saas.core.message.request.query.projection;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Projection {
	private String fieldName;
	@JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingName)
	private Clude clude;

	public Projection() {
		super();
	}

	public Projection(String fieldName, Clude clude) {
		super();
		this.fieldName = fieldName;
		this.clude = clude;
	}

	public enum Clude {
		EXCLUDE, INCLUDE;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Clude getClude() {
		return clude;
	}

	public void setClude(Clude clude) {
		this.clude = clude;
	}

}
