package com.demo.my.base.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.demo.my.base.bean.User;
import com.demo.my.base.common.BaseCommon;

public class DefaultBaseService extends BaseCommon {
	
	public Long getCurrentUserId() {
		return getCurrentUser().getId();
	}
	
	public User getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("userInfo");
		return user;
	}
	
}
