package com.lrb.saas.manager.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuItem {
	@Id
	private String id;
	private String title;
	private String content;
	private String jsx;
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJsx() {
		return jsx;
	}

	public void setJsx(String jsx) {
		this.jsx = jsx;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
