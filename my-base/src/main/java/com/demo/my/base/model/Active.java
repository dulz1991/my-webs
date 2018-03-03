package com.demo.my.base.model;

import java.io.Serializable;
import java.util.Date;

public class Active implements Serializable {

	private static final long serialVersionUID = 1L;

	private String activeTitle;
	private Date endDate;
	private Date updateTime;
	private String activeImg;
	private Date createTime;
	private String activeContent;
	private Date startTime;
	private Long createrId;
	private Date endTime;
	private Long id;
	private String activeCity;
	private Date startDate;
	
	public String getActiveTitle() {
		return activeTitle;
	}
	public void setActiveTitle(String activeTitle) {
		this.activeTitle = activeTitle;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getActiveImg() {
		return activeImg;
	}
	public void setActiveImg(String activeImg) {
		this.activeImg = activeImg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getActiveContent() {
		return activeContent;
	}
	public void setActiveContent(String activeContent) {
		this.activeContent = activeContent;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Long getCreaterId() {
		return createrId;
	}
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActiveCity() {
		return activeCity;
	}
	public void setActiveCity(String activeCity) {
		this.activeCity = activeCity;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
}