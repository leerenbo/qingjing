package com.lrb.saas.core.enums;

public enum ContentType {
	APPLICATION_JSON("application/json");

	private ContentType(String type) {
		this.type = type;
	}

	private String type;

	@Override
	public String toString() {
		return type;
	}

}
