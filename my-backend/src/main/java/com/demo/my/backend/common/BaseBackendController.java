package com.demo.my.backend.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.my.base.common.BaseCommon;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;  
import java.net.URLDecoder;

public class BaseBackendController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        
        request.setAttribute("userInfo", this.getCurrentUser());
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
