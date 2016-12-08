package com.lrb.saas.core.model.qingjing.order;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.lrb.saas.core.annotation.SAASField;

@Entity
public class OrderList {
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
	@SAASField(name = "产品")
	@OneToMany
	private List<OrderItem> orderItems;
	@SAASField(name = "总金额")
	private String money;
	@SAASField(name = "状态")
	private String status;
	@SAASField(name = "快递号")
	private String expressNO;

	@SAASField(name = "创建时间")
	private Date sys_createDateTime;

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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpressNO() {
		return expressNO;
	}

	public void setExpressNO(String expressNO) {
		this.expressNO = expressNO;
	}

}
