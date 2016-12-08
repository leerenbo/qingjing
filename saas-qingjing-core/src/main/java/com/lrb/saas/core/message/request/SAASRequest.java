package com.lrb.saas.core.message.request;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.lrb.saas.core.annotation.SAASField;
import com.lrb.saas.core.validation.group.DELETE;
import com.lrb.saas.core.validation.group.PATCH;
import com.lrb.saas.core.validation.group.POST;
import com.lrb.saas.core.validation.group.PUT;

public class SAASRequest<T> {
	protected String url;
	@SAASField(name = "登陆后持有的token")
	protected String token;

	@SAASField(name = "客户端类型", remark = "PC，WAP，APP")
	@NotBlank(groups = { DELETE.class, PATCH.class, POST.class, PUT.class })
	protected String client;

	@SAASField(name = "租户", remark = "分配的租户号")
	@NotBlank(groups = { DELETE.class, PATCH.class, POST.class, PUT.class })
	protected String tenant;

	@SAASField(name = "销售渠道")
	protected String channel;

	@Valid
	@SAASField(name = "请求内容", remark = "泛型消息体")
	// @NotNull(groups = { DELETE.class, PATCH.class, POST.class, PUT.class })
	protected T body;

	public SAASRequest() {
		super();
	}

	public SAASRequest(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public String toJsonString() {
		return JSON.toJSONString(this);
	}

	public SAASRequest<T> p(String json) {
		return JSON.parseObject(json, this.getClass().getGenericSuperclass());
	}

	@Override
	public String toString() {
		return "SAASRequest [url=" + url + ", token=" + token + ", client=" + client + ", tenant=" + tenant
				+ ", channel=" + channel + ", body=" + body + "]";
	}

}
