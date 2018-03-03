package com.demo.my.base.model;

import java.io.Serializable;
import java.util.Date;

public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleName;
	private Long id;
	private String roleDescription;
	
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
	
	
	
}