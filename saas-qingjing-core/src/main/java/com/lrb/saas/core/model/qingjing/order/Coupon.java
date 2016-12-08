package com.lrb.saas.core.model.qingjing.order;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.lrb.saas.core.annotation.SAASField;

@Entity
public class Coupon {
	@Id
	@SAASField(name = "主键")
	private String id;
	@SAASField(name = "满金额")
	private String enableMoney;
	@SAASField(name = "减金额")
	private String cutMoney;
	@SAASField(name = "优惠券类型")
	private String type;
}
