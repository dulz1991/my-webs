package com.demo.my.base.common;

import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;

import com.demo.my.base.model.User;

public class BaseCommon {
	
	public User getCurrentUser() {
		try {
			Subject subject = SecurityUtils.getSubject();
			User user = (User) subject.getSession().getAttribute(KeyConstant.USER_INFO);
			return user;	
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public Long getCurrentUserId() {
		User user = getCurrentUser();
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}
	
	public boolean isLogin() {
		if (this.getCurrentUser() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public HashMap<String, Object> responseOK(String result){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(ErrorConstant.ERROR_NO, ErrorConstant.ERROR_200);
		resMap.put(ErrorConstant.ERROR_INFO, result);
		return resMap;
	}
	public HashMap<String, Object> responseError(Integer errorNo, String errorInfo){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(ErrorConstant.ERROR_NO, errorNo);
		resMap.put(ErrorConstant.ERROR_INFO, errorInfo);
		return resMap;
	}
	
	public SavedRequest getSavedRequest() {
        SavedRequest savedRequest = null;
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if (session != null) {
            savedRequest = (SavedRequest) session.getAttribute("shiroSavedRequest");
        }
        return savedRequest;
    }

}
