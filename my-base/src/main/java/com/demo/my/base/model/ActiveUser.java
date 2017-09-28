package com.demo.my.base.model;

import java.io.Serializable;
import java.util.Date;

public class ActiveUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long activeId;
	private Long id;
	private Date createTime;
	private Long userId;
	
	public Long getActiveId() {
		return activeId;
	}
	public void setActiveId(Long activeId) {
		this.activeId = activeId;
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
	
	
	
}