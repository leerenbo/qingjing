package com.lrb.saas.core.model.qingjing.order;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.lrb.saas.core.annotation.SAASField;

@Entity
public class Address {
	@Id
	@SAASField(name = "主键")
	private String id;
	@SAASField(name = "姓名")
	private String name;
	@SAASField(name = "手机号")
	private String phoneNumber;
	@SAASField(name = "地址")
	private String location;
	@SAASField(name = "微信号openid")
	private String openid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
