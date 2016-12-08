package com.lrb.saas.core.model.qingjing.production;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.lrb.saas.core.annotation.SAASField;

@Entity
public class Production {
	@Id
	@SAASField(name="主键")
	private String id;
	@SAASField(name="产品名称")
	private String name;
	@SAASField(name="产品子名称")
	private String subName;
	
	@SAASField(name="产品简短描述")
	private String shotDescrpiton;
	@SAASField(name="产品缩略图地址")
	private String thumbnailUri;
	@SAASField(name="产品金额")
	private String price;
	
	@OneToMany(mappedBy="production")
	private List<ProductionType> productionTypes;
	
	@SAASField(name="产品详细描述")
	private String descrption;

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

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getShotDescrpiton() {
		return shotDescrpiton;
	}

	public void setShotDescrpiton(String shotDescrpiton) {
		this.shotDescrpiton = shotDescrpiton;
	}

	public String getThumbnailUri() {
		return thumbnailUri;
	}

	public void setThumbnailUri(String thumbnailUri) {
		this.thumbnailUri = thumbnailUri;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<ProductionType> getProductionTypes() {
		return productionTypes;
	}

	public void setProductionTypes(List<ProductionType> productionTypes) {
		this.productionTypes = productionTypes;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

}
