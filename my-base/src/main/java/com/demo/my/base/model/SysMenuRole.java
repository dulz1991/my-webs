package com.demo.my.base.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

public class SysMenuRole implements Serializable {

	private static final long serialVersionUID = -1527082784380L;

	private Long sysRoleId;
	private Long id;
	private String sysMenuId;
	
	public Long getSysRoleId() {
		return sysRoleId;
	}
	public void setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSysMenuId() {
		return sysMenuId;
	}
	public void setSysMenuId(String sysMenuId) {
		this.sysMenuId = sysMenuId;
	}
	
	
	
}