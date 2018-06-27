package com.demo.my.base.model;

import java.io.Serializable;

public class SysRole implements Serializable {

	private static final long serialVersionUID = -1523452275758L;

	/**
	* 权限名称
	**/
	private String roleName;
	/**
	* 
	**/
	private Long id;
	/**
	* 权限描述
	**/
	private String roleDescription;
	/**
	* 权限编码
	**/
	private String roleCode;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
	
}