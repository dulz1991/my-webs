package com.demo.my.user.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

	private List<String> uncheckUrls;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){     
		String requestUrl = request.getRequestURI(); 
		if(uncheckUrls.contains(requestUrl)){ 
			return false; 
		}else{ 
			return true; 
		}
	}
	
	public List<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}
}
