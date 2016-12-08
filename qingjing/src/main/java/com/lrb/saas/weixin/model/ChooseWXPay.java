package com.lrb.saas.weixin.model;

import org.apache.commons.codec.digest.DigestUtils;

import com.tencent.common.Configure;

public class ChooseWXPay {
	private String appId;
	private String timeStamp;
	private String nonceStr;
	private String prepay_id;
	private String signType;
	private String paySign;

	private static String signaturePattern = "appId=%s&nonceStr=%s&package=%s&signType=%s&timeStamp=%s&key=%s";

	public ChooseWXPay(String appId, Long timeStamp, String nonceStr, String prepay_id) {
		super();
		this.appId = appId;
		this.timeStamp = timeStamp + "";
		this.nonceStr = nonceStr;
		this.prepay_id = "prepay_id=" + prepay_id;
		this.signType = "MD5";
		String message = String.format(signaturePattern, appId, nonceStr, this.prepay_id, signType, timeStamp, Configure.getKey());
		this.paySign = DigestUtils.md5Hex(message).toUpperCase();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

}
