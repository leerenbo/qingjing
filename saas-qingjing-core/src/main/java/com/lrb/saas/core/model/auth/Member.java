/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.lrb.saas.core.model.auth;

import com.lrb.saas.core.model.auth.Role;
import com.lrb.saas.core.model.auth.Tenant;
import java.util.HashSet;

/**
 * 用户
 * 
 */
public class Member {
	/**
	 * 主键
	 */
	private String id = "";

	/**
	 * 用户名
	 */
	private String username = "";

	/**
	 * 密码
	 */
	private String password = "";

	/**
	 * 邮箱
	 */
	private String email = "";

	/**
	 * 手机号
	 */
	private String mobileNumber = "";

	/**
	 * 微信openid，用于扫码登陆
	 */
	private String weixinOpenid = "";

	/**
	 * 用于QQ登陆
	 */
	private String qqOpenid = "";

	/**
	 * 用于微博登陆
	 */
	private String weiboOpenid = "";

	/**
	 * 角色
	 */
	private HashSet<Role> roles = new HashSet<Role>();

	/**
	 * 租户
	 */
	private Tenant tenant = null;

	// Start of user code (user defined attributes for Member)

	// End of user code

	/**
	 * The constructor.
	 */
	public Member() {
		// Start of user code constructor for Member)
		super();
		// End of user code
	}

	/**
	 * 获取所有角色的权限并去重。
	 */
	public void allSAASInterface() {
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

	/**
	 * Returns username.
	 * 
	 * @return username 用户名
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets a value to attribute username.
	 * 
	 * @param newUsername
	 *            用户名
	 */
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	/**
	 * Returns password.
	 * 
	 * @return password 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets a value to attribute password.
	 * 
	 * @param newPassword
	 *            密码
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	/**
	 * Returns email.
	 * 
	 * @return email 邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets a value to attribute email.
	 * 
	 * @param newEmail
	 *            邮箱
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	/**
	 * Returns mobileNumber.
	 * 
	 * @return mobileNumber 手机号
	 */
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	/**
	 * Sets a value to attribute mobileNumber.
	 * 
	 * @param newMobileNumber
	 *            手机号
	 */
	public void setMobileNumber(String newMobileNumber) {
		this.mobileNumber = newMobileNumber;
	}

	/**
	 * Returns weixinOpenid.
	 * 
	 * @return weixinOpenid 微信openid，用于扫码登陆
	 */
	public String getWeixinOpenid() {
		return this.weixinOpenid;
	}

	/**
	 * Sets a value to attribute weixinOpenid.
	 * 
	 * @param newWeixinOpenid
	 *            微信openid，用于扫码登陆
	 */
	public void setWeixinOpenid(String newWeixinOpenid) {
		this.weixinOpenid = newWeixinOpenid;
	}

	/**
	 * Returns qqOpenid.
	 * 
	 * @return qqOpenid 用于QQ登陆
	 */
	public String getQqOpenid() {
		return this.qqOpenid;
	}

	/**
	 * Sets a value to attribute qqOpenid.
	 * 
	 * @param newQqOpenid
	 *            用于QQ登陆
	 */
	public void setQqOpenid(String newQqOpenid) {
		this.qqOpenid = newQqOpenid;
	}

	/**
	 * Returns weiboOpenid.
	 * 
	 * @return weiboOpenid 用于微博登陆
	 */
	public String getWeiboOpenid() {
		return this.weiboOpenid;
	}

	/**
	 * Sets a value to attribute weiboOpenid.
	 * 
	 * @param newWeiboOpenid
	 *            用于微博登陆
	 */
	public void setWeiboOpenid(String newWeiboOpenid) {
		this.weiboOpenid = newWeiboOpenid;
	}

	/**
	 * Returns roles.
	 * 
	 * @return roles 角色
	 */
	public HashSet<Role> getRoles() {
		return this.roles;
	}

	/**
	 * Returns tenant.
	 * 
	 * @return tenant 租户
	 */
	public Tenant getTenant() {
		return this.tenant;
	}

	/**
	 * Sets a value to attribute tenant.
	 * 
	 * @param newTenant
	 *            租户
	 */
	public void setTenant(Tenant newTenant) {
		this.tenant = newTenant;
	}

}
