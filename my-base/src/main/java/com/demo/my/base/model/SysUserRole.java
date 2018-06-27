package com.demo.my.base.model;

import java.io.Serializable;

public class SysUserRole implements Serializable {

	private static final long serialVersionUID = -1523546348046L;

	/**
	* 系统用户id
	**/
	private Long sysUserId;
	/**
	* 
	**/
	private Long id;
	/**
	* 权限编码
	**/
	private String sysRoleCode;
	
	public Long getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSysRoleCode() {
		return sysRoleCode;
	}
	public void setSysRoleCode(String sysRoleCode) {
		this.sysRoleCode = sysRoleCode;
	}
	
	
	
}