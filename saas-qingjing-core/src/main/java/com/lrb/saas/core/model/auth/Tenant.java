/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.lrb.saas.core.model.auth;

import com.lrb.saas.core.model.auth.TenantType;
// Start of user code (user defined imports)

// End of user code

/**
 * 租户
 * 
 * @author 李仁博
 */
public class Tenant {
	/**
	 * 主键
	 */
	private String id = "";

	/**
	 * 名称
	 */
	private String name = "";

	/**
	 * 租户类型，个人，企业
	 */
	private TenantType tenantType = null;

	/**
	 * 积分奖励
	 */
	private double reward = 0D;

	// Start of user code (user defined attributes for Tenant)

	// End of user code

	/**
	 * The constructor.
	 */
	public Tenant() {
		// Start of user code constructor for Tenant)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Tenant)

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
	 * Returns name.
	 * @return name 名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a value to attribute name. 
	 * @param newName 名称
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns tenantType.
	 * @return tenantType 租户类型，个人，企业
	 */
	public TenantType getTenantType() {
		return this.tenantType;
	}

	/**
	 * Sets a value to attribute tenantType. 
	 * @param newTenantType 租户类型，个人，企业
	 */
	public void setTenantType(TenantType newTenantType) {
		this.tenantType = newTenantType;
	}

	/**
	 * Returns reward.
	 * @return reward 积分奖励
	 */
	public double getReward() {
		return this.reward;
	}

	/**
	 * Sets a value to attribute reward. 
	 * @param newReward 积分奖励
	 */
	public void setReward(double newReward) {
		this.reward = newReward;
	}

	public static void main(String[] args) {
		System.out.println(TenantType.values()[1].name());
	}
}
