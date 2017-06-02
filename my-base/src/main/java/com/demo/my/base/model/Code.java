package com.demo.my.base.model;

import java.io.Serializable;
import java.util.Date;

public class Code implements Serializable {

	private static final long serialVersionUID = 10006L;
	
	private Long id;
	private Date createTime;
	private Date updateTime;
	private String item;
	private String content;
	private Long fatherId;
	private Long codeId;
	private Long itemOrder;
	private Integer status;
	private Integer codeLevel;
	
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
	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the itemOrder
	 */
	public Long getItemOrder() {
		return itemOrder;
	}
	/**
	 * @param itemOrder the itemOrder to set
	 */
	public void setItemOrder(Long itemOrder) {
		this.itemOrder = itemOrder;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getCodeId() {
		return codeId;
	}
	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}
	public Integer getCodeLevel() {
		return codeLevel;
	}
	public void setCodeLevel(Integer codeLevel) {
		this.codeLevel = codeLevel;
	}
}
