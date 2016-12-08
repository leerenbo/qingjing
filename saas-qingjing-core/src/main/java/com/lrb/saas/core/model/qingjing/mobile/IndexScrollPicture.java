package com.lrb.saas.core.model.qingjing.mobile;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import com.lrb.saas.core.annotation.SAASField;
import com.lrb.saas.core.validation.group.POST;
import com.lrb.saas.core.validation.group.PUT;

@Entity
public class IndexScrollPicture {
	@Id
	@SAASField(name = "主键")
	@NotBlank(groups = { POST.class, PUT.class },message="主键不能为空")
	private String id;
	@SAASField(name = "图片地址")
	@NotBlank(groups = { POST.class, PUT.class },message="图片地址不能为空")
	private String imgUri;
	@SAASField(name = "点击跳转地址")
	private String clickUri;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgUri() {
		return imgUri;
	}

	public void setImgUri(String imgUri) {
		this.imgUri = imgUri;
	}

	public String getClickUri() {
		return clickUri;
	}

	public void setClickUri(String clickUri) {
		this.clickUri = clickUri;
	}

}
