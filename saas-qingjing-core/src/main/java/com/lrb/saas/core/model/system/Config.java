/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.lrb.saas.core.model.system;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Config.
 * 
 * @author 李仁博
 */
public class Config {
	/**
	 * 主键key
	 */
	private String id = "";

	/**
	 * Description of the property value.
	 */
	private String value = "";

	// Start of user code (user defined attributes for Config)

	// End of user code

	/**
	 * The constructor.
	 */
	public Config() {
		// Start of user code constructor for Config)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Config)

	// End of user code
	/**
	 * Returns id.
	 * @return id 主键key
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets a value to attribute id. 
	 * @param newId 主键key
	 */
	public void setId(String newId) {
		this.id = newId;
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
