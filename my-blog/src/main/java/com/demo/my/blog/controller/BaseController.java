package com.demo.my.blog.controller.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.my.base.common.BaseCommon;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.model.User;
import com.demo.my.base.service.UserService;

public class BaseController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response;
    protected Map<String, Object> resMap;
    protected String cookieName;
    
    @Autowired
    private UserService userService;
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{  
        this.request = request;
        this.response = response;
        this.resMap = responseOK("");
        this.cookieName = this.request.getParameter(KeyConstant.COOKIE_USER);
        
        User user = this.getCurrentUser(); 
		if (user != null) {
			request.setAttribute("role", user.getRole());
			request.setAttribute("isLogin", true);
			request.setAttribute("username", user.getUsername());
		} else {
			request.setAttribute("role", -1);
			request.setAttribute("isLogin", false);
		}
        
        //vue post����
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true"); // ������� cookie
        // Request methods you wish to allow
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
        // Request headers you wish to allow
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
    }
	
    public User getUserFromCookie(){
    	/*String username = request.getParameter(cookieName);*/
    	String username = this.cookieName;
    	//username = Base64Util.decodeBase64(username);
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
    
    public Map<String, Object> getParmMap() {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		
		//遍历 request
		Enumeration paramNames = request.getParameterNames();  
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();  
			String[] paramValues = request.getParameterValues(paramName);  
			if (paramValues.length == 1) {  
				String paramValue = paramValues[0];  
				if (paramValue.length() != 0) {  
					try {
						parmMap.put(paramName, URLDecoder.decode(paramValue, "utf-8"));	
					} catch (Exception e) {
						parmMap.put(paramName, "");
					}
				}  
			}  
		}
		
		return parmMap;
	}

}
