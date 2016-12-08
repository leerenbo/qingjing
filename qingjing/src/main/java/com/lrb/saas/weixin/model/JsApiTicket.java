package com.lrb.saas.weixin.model;

import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;

public class JsApiTicket {
	private String noncestr;
	private String jsapi_ticket;
	private Long timestamp;
	private String url;
	private String signature;
	private String appId;

	private static String signaturePattern = "jsapi_ticket=%s&noncestr=%s&timestamp=%d&url=%s";

	public JsApiTicket(String noncestr, String jsapi_ticket, Long timestamp, String url, String appId) {
		super();
		this.noncestr = noncestr;
		this.jsapi_ticket = jsapi_ticket;
		this.timestamp = timestamp;
		this.url = url;
		this.appId = appId;
		String message=String.format(signaturePattern, jsapi_ticket, noncestr, timestamp, url);
		signature = DigestUtils.sha1Hex(message);
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getJsapi_ticket() {
		return jsapi_ticket;
	}

	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}


	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
