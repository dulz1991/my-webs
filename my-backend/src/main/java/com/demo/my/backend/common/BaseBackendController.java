package com.demo.my.backend.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.my.base.common.BaseCommon;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;  
import java.lang.reflect.Method;  
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
	
	public Map<String, Object> getParmMap(Object obj) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		
		// 得到类对象
		if(obj!=null){
			Class clazz = (Class) obj.getClass();
	        /* 得到类中的所有属性集合 */
	        Field[] fs = clazz.getDeclaredFields();
	        for(int i = 0 ; i < fs.length; i++){
	        	Field f = fs[i];
	        	f.setAccessible(true); // 设置些属性是可以访问的
	        	Object val = new Object();
	        	try {
	        		val = f.get(obj); // 得到此属性的值
	        		if(null != val && StringUtils.isNotBlank(val.toString())){
	        			val = URLDecoder.decode(val.toString(), "utf-8");
	        			parmMap.put(f.getName(), val);// 设置键值
	        		}
	        	} catch (IllegalArgumentException e) {
	        		e.printStackTrace();
	        	} catch (IllegalAccessException e) {
	        		e.printStackTrace();
	        	} catch (UnsupportedEncodingException e) {
	        		e.printStackTrace();
	        	}
	        }
		}
       
       return parmMap;
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
