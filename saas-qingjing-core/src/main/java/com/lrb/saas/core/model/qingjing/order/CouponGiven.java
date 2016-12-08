package com.lrb.saas.core.model.qingjing.order;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.lrb.saas.core.annotation.SAASField;

@Entity
public class CouponGiven {
	@Id
	@SAASField(name = "主键")
	private String id;
	@SAASField(name = "微信号openid")
	private String openid;
	@SAASField(name = "已发送优惠券")
	@OneToMany
	private List<Coupon> coupons;

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

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

}
