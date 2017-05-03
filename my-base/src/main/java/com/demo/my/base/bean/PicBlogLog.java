package com.demo.my.base.bean;

import java.io.Serializable;
import java.util.Date;

public class PicBlogLog implements Serializable {
	
	private static final long serialVersionUID = 10009L;
	
	private Long id;
	private Date createTime;
	private Date updateTime;
	private String remark;
	private Long userId;
	private Long picBlogId;
	
	//parm
	private String username;
	private String createTimeStr;
	
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPicBlogId() {
		return picBlogId;
	}
	public void setPicBlogId(Long picBlogId) {
		this.picBlogId = picBlogId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	
	
}
