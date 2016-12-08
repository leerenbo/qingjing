package com.lrb.saas.core.model.qingjing.production;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.lrb.saas.core.annotation.SAASField;

@Entity
public class ProductionType {
	@Id
	@SAASField(name="主键")
	private String id;

	@SAASField(name = "产品类型")
	private String name;
	@ElementCollection
	@SAASField(name = "多个产品轮播图地址")
	private List<String> imgsUri;

	@ManyToOne
	@SAASField(name = "产品",getUrl="http://localhost:80/saas-qingjing-shop/production")
	private Production production;

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

	public List<String> getImgsUri() {
		return imgsUri;
	}

	public void setImgsUri(List<String> imgsUri) {
		this.imgsUri = imgsUri;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

}
