/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.lrb.saas.core.model.auth;

import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.lrb.saas.core.annotation.SAASField;

// Start of user code (user defined imports)

// End of user code

/**
 * 接口属性 Empty comment Empty comment
 * 
 * @author 李仁博
 */
public class SAASFieldInfo {
	/**
	 * 主键
	 */
	private String id = "";
	/**
	 * 属性名
	 */
	private String fieldName = "";
	/**
	 * 属性中文名
	 */
	private String name = "";
	/**
	 * Description of the property sAASValidationInfo.
	 */
	public SAASValidationInfo saasValidationInfo = null;

	/**
	 * 数据获取url
	 */
	private String dictionaryUrl;
	private String getUrl;
	/**
	 * 属性类型 classname
	 */
	private String type = "";

	/**
	 * 备注
	 */
	private String remark = "";

	/**
	 * ui组件
	 */
	private String uiComponent = "";

	private String uiAttributes = "";

	/**
	 * 内部属性
	 */
	private List<SAASFieldInfo> innerSAASFieldInfos = new ArrayList<SAASFieldInfo>();

	/**
	 * 不可修改
	 */
	private Boolean scaned = false;

	/**
	 * The constructor.
	 */
	public SAASFieldInfo() {
		// Start of user code constructor for SAASFieldInfo)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for SAASFieldInfo)

	public SAASFieldInfo(SAASField saasField) {
		name = saasField.name();
		this.getUrl=saasField.getUrl();
		this.dictionaryUrl = saasField.dictionaryUrl();
		this.remark = saasField.remark();
		this.uiComponent = saasField.uiComponent();
		this.uiAttributes=saasField.uiAttributes();
		this.scaned = true;
	}

	// End of user code
	/**
	 * Returns name.
	 * 
	 * @return name 属性中文名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a value to attribute name.
	 * 
	 * @param newName
	 *            属性中文名
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	public SAASValidationInfo getSaasValidationInfo() {
		return saasValidationInfo;
	}

	public void setSaasValidationInfo(SAASValidationInfo saasValidationInfo) {
		this.saasValidationInfo = saasValidationInfo;
	}

	public Boolean getScaned() {
		return scaned;
	}

	public void setScaned(Boolean scaned) {
		this.scaned = scaned;
	}

	/**
	 * Returns dictionaryUrl.
	 * 
	 * @return dictionaryUrl 数据获取url
	 */
	public String getDictionaryUrl() {
		return this.dictionaryUrl;
	}

	/**
	 * Sets a value to attribute dictionaryUrl.
	 * 
	 * @param newDictionaryUrl
	 *            数据获取url
	 */
	public void setDictionaryUrl(String newDictionaryUrl) {
		this.dictionaryUrl = newDictionaryUrl;
	}

	/**
	 * Returns type.
	 * 
	 * @return type 属性类型 classname
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets a value to attribute type.
	 * 
	 * @param newType
	 *            属性类型 classname
	 */
	public void setType(String newType) {
		this.type = newType;
	}

	/**
	 * Returns remark.
	 * 
	 * @return remark 备注
	 */
	public String getRemark() {
		return this.remark;
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

	/**
	 * Returns id.
	 * 
	 * @return id 主键
	 */
	public String getId() {
		return this.id;
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


	public List<SAASFieldInfo> getInnerSAASFieldInfos() {
		return innerSAASFieldInfos;
	}

	public void setInnerSAASFieldInfos(List<SAASFieldInfo> innerSAASFieldInfos) {
		this.innerSAASFieldInfos = innerSAASFieldInfos;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getUiComponent() {
		return uiComponent;
	}

	public void setUiComponent(String uiComponent) {
		this.uiComponent = uiComponent;
	}

	public String getUiAttributes() {
		return uiAttributes;
	}

	public void setUiAttributes(String uiAttributes) {
		this.uiAttributes = uiAttributes;
	}

	public String getGetUrl() {
		return getUrl;
	}

	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}

}
