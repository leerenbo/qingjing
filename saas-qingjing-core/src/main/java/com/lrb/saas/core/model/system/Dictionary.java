/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.lrb.saas.core.model.system;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Dictionary.
 * 
 * @author 李仁博
 */
public class Dictionary {
	/**
	 * 主键
	 */
	private String id = "";

	/**
	 * 类别
	 */
	private String type = "";

	/**
	 * 
	 */
	private String key = "";

	/**
	 * Description of the property value.
	 */
	private String value = "";

	// Start of user code (user defined attributes for Dictionary)

	// End of user code

	/**
	 * The constructor.
	 */
	public Dictionary() {
		// Start of user code constructor for Dictionary)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Dictionary)

	// End of user code
	/**
	 * Returns id.
	 * @return id 主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets a value to attribute id. 
	 * @param newId 主键
	 */
	public void setId(String newId) {
		this.id = newId;
	}

	/**
	 * Returns type.
	 * @return type 类别
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 类别
	 */
	public void setType(String newType) {
		this.type = newType;
	}

	/**
	 * Returns key.
	 * @return key 
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Sets a value to attribute key. 
	 * @param newKey 
	 */
	public void setKey(String newKey) {
		this.key = newKey;
	}

	/**
	 * Returns value.
	 * @return value 
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets a value to attribute value. 
	 * @param newValue 
	 */
	public void setValue(String newValue) {
		this.value = newValue;
	}

}
