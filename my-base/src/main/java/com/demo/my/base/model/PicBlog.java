package com.demo.my.base.model;

import java.io.Serializable;
import java.util.Date;

public class PicBlog implements Serializable {
	
	private static final long serialVersionUID = 10003L;
	
	private Long id;
	private Date createTime;
	private Date updateTime;
	private Long userId;
	private String title;
	private String content;
	private String showPic;
	private Long click;
	private String description;
	private Integer status;
	
	// parm
	private String username;
	
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the showPic
	 */
	public String getShowPic() {
		return showPic;
	}
	/**
	 * @param showPic the showPic to set
	 */
	public void setShowPic(String showPic) {
		this.showPic = showPic;
	}
	/**
	 * @return the click
	 */
	public Long getClick() {
		return click;
	}
	/**
	 * @param click the click to set
	 */
	public void setClick(Long click) {
		this.click = click;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

}
