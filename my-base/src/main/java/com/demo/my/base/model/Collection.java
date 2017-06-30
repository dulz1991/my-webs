package com.demo.my.base.model;

import java.io.Serializable;
import java.util.Date;

public class Collection implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long discoveryId;
	private Long blogId;
	private Long id;
	private Date createTime;
	private Long userId;
	
	public Long getDiscoveryId() {
		return discoveryId;
	}
	public void setDiscoveryId(Long discoveryId) {
		this.discoveryId = discoveryId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	
	
	
}