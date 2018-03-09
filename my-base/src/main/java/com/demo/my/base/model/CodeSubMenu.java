package com.demo.my.base.model;

import java.io.Serializable;

public class CodeSubMenu implements Serializable {

	private static final long serialVersionUID = 10004L;

	private Long id;
	private String name;
	private Long fatherId;
	private Integer status;
	private Integer orderBy;
	private String remark;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the fatherId
	 */
	public Long getFatherId() {
		return fatherId;
	}
	/**
	 * @param fatherId the fatherId to set
	 */
	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
