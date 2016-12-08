package com.lrb.saas.core.model.qingjing.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.lrb.saas.core.model.qingjing.production.ProductionType;

@Entity
public class OrderItem {
	@Id
	private String id;
	@OneToOne
	private ProductionType productionType;
	private Integer count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProductionType getProductionType() {
		return productionType;
	}

	public void setProductionType(ProductionType productionType) {
		this.productionType = productionType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
