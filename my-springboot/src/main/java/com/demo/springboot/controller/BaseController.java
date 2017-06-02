package com.demo.springboot.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.my.base.common.BaseCommon;

public class BaseController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response;
    protected Map<String, Object> resMap;
    
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
	
}
