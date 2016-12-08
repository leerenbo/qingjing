package com.lrb.saas.core.model.auth;

public enum TenantType {

	PERSEN("个人"), ENTERPRISE("企业");

	private final String text;

	private TenantType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
