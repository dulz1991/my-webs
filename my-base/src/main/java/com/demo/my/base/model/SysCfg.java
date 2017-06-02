package com.demo.my.base.model;

import java.io.Serializable;

public class SysCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String remark; //备注
	private String countJson; //文章数量
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCountJson() {
		return countJson;
	}
	public void setCountJson(String countJson) {
		this.countJson = countJson;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}