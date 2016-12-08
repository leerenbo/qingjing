package com.lrb.saas.core.model.qingjing.order;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.lrb.saas.core.annotation.SAASField;

@Entity
public class Pay {
	@Id
	private String id;
	@SAASField(name = "微信号openid")
	private String openid;
	@SAASField(name = "订单")
	@OneToOne
	private OrderList order;
	@SAASField(name = "微信支付号")
	private String weiXinPayId;
	@SAASField(name = "支付金额")
	private String money;
	@SAASField(name = "支付状态")
	private String status;
	@SAASField(name = "创建时间")
	private Date sys_createDateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public OrderList getOrder() {
		return order;
	}

	public void setOrder(OrderList order) {
		this.order = order;
	}

	public String getWeiXinPayId() {
		return weiXinPayId;
	}

	public void setWeiXinPayId(String weiXinPayId) {
		this.weiXinPayId = weiXinPayId;
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

}
