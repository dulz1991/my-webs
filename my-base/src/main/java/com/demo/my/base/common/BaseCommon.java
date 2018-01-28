package com.demo.my.base.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
	
	public static HashMap<String, Object> responseOK(String result){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(ErrorConstant.ERROR_NO, ErrorConstant.ERROR_200);
		resMap.put(ErrorConstant.ERROR_INFO, result);
		return resMap;
	}
	public static HashMap<String, Object> responseError(Integer errorNo, String errorInfo){
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

	//首字母转小写
	public static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	//首字母转大写
	public static String toUpperCaseFirstOne(String s){
	  if(Character.isUpperCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	public static Map<String, Object> obj2Map(Object obj) {
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
	
}
