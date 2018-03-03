package com.demo.my.base.model;

import java.io.Serializable;

public class SysCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String key; 
	private String value; 
	private String flag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	} 
	
	
	
}