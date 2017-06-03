package com.demo.springboot.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.my.base.common.BaseCommon;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.model.User;
import com.demo.my.base.service.UserService;
import com.demo.my.base.util.Base64Util;

public class BaseController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response;
    protected Map<String, Object> resMap;
    
    @Autowired
    private UserService userService;
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{  
        this.request = request;
        this.response = response;
        this.resMap = responseOK("");
        
        //isLogin
        boolean isLogin = this.isLogin();
        request.setAttribute("isLogin", isLogin);
        this.resMap.put("isLogin", isLogin);
        
        //vue post跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 允许带上 cookie
        // Request methods you wish to allow
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
        // Request headers you wish to allow
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
    }
	
    public User getUserFromCookie(){
    	String username = request.getParameter(KeyConstant.COOKIE_USER);
    	username = Base64Util.decodeBase64(username);
    	User user = userService.getByUsername(username);
    	if(user==null){
    		return null;
    	}
    	return user;
    }
    
    public Long getUserIdFromCookie(){
    	User user = getUserFromCookie();
    	if(user==null){
    		return null;
    	}
    	return user.getId();
    }
}
