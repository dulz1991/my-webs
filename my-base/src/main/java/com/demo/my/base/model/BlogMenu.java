package com.demo.my.base.model;

import java.io.Serializable;

public class BlogMenu implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10007L;
	
	private String name;
	private Long id;
	
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

	
}
