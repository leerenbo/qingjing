/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.lrb.saas.core.model.auth;

import java.util.ArrayList;
// Start of user code (user defined imports)
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lrb.saas.core.annotation.SAASInterface;
import com.lrb.saas.core.enums.ContentType;
import com.lrb.saas.core.enums.HttpMethod;

// End of user code

/**
 * 接口对象
 * 
 * @author 李仁博
 */
public class SAASInterfaceInfo {
	/**
	 * 主键
	 */
	private String id = "";
	/**
	 * 接口名称
	 */
	private String name = "";
	/**
	 * 接口地址
	 */
	private String uri = "";

	/**
	 * 接口方法
	 */
	private HttpMethod httpMethod = null;

	/**
	 * 请求类型
	 */
	@JSONField(serialzeFeatures = { SerializerFeature.WriteEnumUsingToString })
	private ContentType contentType = null;

	/**
	 * 标识，是否启用
	 */
	private boolean enable = true;

	/**
	 * 备注
	 */
	private String remark = "";

	/**
	 * 请求参数
	 */
	private List<SAASFieldInfo> requestFields = new ArrayList<SAASFieldInfo>();

	/**
	 * 响应参数
	 */
	private List<SAASFieldInfo> responseFields = new ArrayList<SAASFieldInfo>();

	/**
	 * The constructor.
	 */
	public SAASInterfaceInfo() {
		super();
	}

	public SAASInterfaceInfo(SAASInterface saasInterface, String requestURI, String method) {
		id = saasInterface.id().name();
		remark = saasInterface.remark();
		name = saasInterface.name();
		uri = requestURI;
		httpMethod = HttpMethod.valueOf(method);
	}

	/**
	 * Returns enable.
	 * 
	 * @return enable 标识，是否启用
	 */
	public boolean getEnable() {
		return this.enable;
	}

	/**
	 * Returns httpMethod.
	 * 
	 * @return httpMethod 接口方法
	 */
	public HttpMethod getHttpMethod() {
		return this.httpMethod;
	}

	/**
	 * Returns id.
	 * 
	 * @return id 主键
	 */
	public String getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	/**
	 * Returns remark.
	 * 
	 * @return remark 备注
	 */
	public String getRemark() {
		return this.remark;
	}

	public List<SAASFieldInfo> getRequestFields() {
		return requestFields;
	}

	public List<SAASFieldInfo> getResponseFields() {
		return responseFields;
	}

	/**
	 * Sets a value to attribute enable.
	 * 
	 * @param newEnable
	 *            标识，是否启用
	 */
	public void setEnable(boolean newEnable) {
		this.enable = newEnable;
	}

	/**
	 * Sets a value to attribute httpMethod.
	 * 
	 * @param newHttpMethod
	 *            接口方法
	 */
	public void setHttpMethod(HttpMethod newHttpMethod) {
		this.httpMethod = newHttpMethod;
	}

	/**
	 * Sets a value to attribute id.
	 * 
	 * @param newId
	 *            主键
	 */
	public void setId(String newId) {
		this.id = newId;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets a value to attribute remark.
	 * 
	 * @param newRemark
	 *            备注
	 */
	public void setRemark(String newRemark) {
		this.remark = newRemark;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public void setRequestFields(List<SAASFieldInfo> requestFields) {
		this.requestFields = requestFields;
	}

	public void setResponseFields(List<SAASFieldInfo> responseFields) {
		this.responseFields = responseFields;
	}

}
