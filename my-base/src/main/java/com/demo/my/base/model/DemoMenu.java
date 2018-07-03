package com.demo.my.base.model;

import java.io.Serializable;

public class DemoMenu implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10007L;
	
	private String name;
	private Long id;
	private int orderNum;
	private int enable;
	private String icon;
	private String pathWindows;
	private String pathLinux;
	private Long parentId;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
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
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPathWindows() {
		return pathWindows;
	}
	public void setPathWindows(String pathWindows) {
		this.pathWindows = pathWindows;
	}
	public String getPathLinux() {
		return pathLinux;
	}
	public void setPathLinux(String pathLinux) {
		this.pathLinux = pathLinux;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	
}
