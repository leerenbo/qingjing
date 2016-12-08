/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.lrb.saas.core.model.auth;

import com.lrb.saas.core.model.auth.SAASInterfaceInfo;
import java.util.HashSet;
// Start of user code (user defined imports)

// End of user code

/**
 * 角色对象
 * 
 * @author 李仁博
 */
public class Role {
	/**
	 * Description of the property sAASInterfaces.
	 */
	public HashSet<SAASInterfaceInfo> sAASInterfaces = new HashSet<SAASInterfaceInfo>();

	/**
	 * 主键
	 */
	private String id = "";

	/**
	 * 接口权限
	 */
	private HashSet<SAASInterfaceInfo> saasInterfacesDescription = new HashSet<SAASInterfaceInfo>();

	// Start of user code (user defined attributes for Role)

	// End of user code

	/**
	 * The constructor.
	 */
	public Role() {
		// Start of user code constructor for Role)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Role)

	// End of user code
	/**
	 * Returns sAASInterfaces.
	 * @return sAASInterfaces 
	 */
	public HashSet<SAASInterfaceInfo> getSAASInterfaces() {
		return this.sAASInterfaces;
	}

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
	 * Returns saasInterfacesDescription.
	 * @return saasInterfacesDescription 接口权限
	 */
	public HashSet<SAASInterfaceInfo> getSaasInterfacesDescription() {
		return this.saasInterfacesDescription;
	}

}
