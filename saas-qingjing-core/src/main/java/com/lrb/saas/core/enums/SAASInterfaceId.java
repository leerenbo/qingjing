package com.lrb.saas.core.enums;

public enum SAASInterfaceId {
	ALL, //
	DEFAULT, //
	SHOP_IndexScrollPicture_POST("/saas-qingjing-shop/news", HttpMethod.POST, ContentType.APPLICATION_JSON); //

	private SAASInterfaceId() {

	}

	private SAASInterfaceId(String uri, HttpMethod method) {
		this.uri = uri;
		this.method = method;
	}

	private SAASInterfaceId(String uri, HttpMethod method, ContentType contentType) {
		this.uri = uri;
		this.method = method;
		this.contentType = contentType;
	}

	String uri;
	HttpMethod method;
	ContentType contentType;
}
