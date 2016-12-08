package com.lrb.saas.core.message.request.query.sort;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Order {
	private String fieldName;
	@JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingName)
	private Direction direction;

	public enum Direction {
		ASC, DESC;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
