package com.tinyurl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TINY_URL_LINK")
public class TinyUrlLinkDo {

	private String tinyUrlId;
	
	private String fullUrl;
	
	private Integer accessCount;

	@Id
	@Column(name = "TINY_URL_ID")
	public String getTinyUrlId() {
		return tinyUrlId;
	}

	public void setTinyUrlId(String tinyUrlId) {
		this.tinyUrlId = tinyUrlId;
	}

	@Column(name = "FULL_URL")
	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	@Column(name = "ACCESS_COUNTS")
	public Integer getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}
	
	
}
