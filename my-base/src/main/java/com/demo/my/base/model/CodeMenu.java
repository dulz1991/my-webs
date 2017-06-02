package com.demo.my.base.model;

import java.io.Serializable;

public class CodeMenu implements Serializable {

	private static final long serialVersionUID = 10005L;

	private Long id;
	private String name;
	private Integer orderBy;
	
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
	 * @return the orderBy
	 */
	public Integer getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
