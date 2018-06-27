package com.demo.my.base.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;

import com.demo.my.base.model.User;

public class BaseCommon extends ErrorConstant {
	
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
	
	public Map<String, Object> parmMap() {
		return new HashMap<String, Object>();
	}
	
	/*首字母小写*/
	public static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	/*首字母大写*/
	public static String toUpperCaseFirstOne(String s){
	  if(Character.isUpperCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
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

	/*对象转map*/
	public static Map<String, Object> obj2Map(Object obj) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		
		if(obj!=null){
			Class clazz = (Class) obj.getClass();
	        Field[] fs = clazz.getDeclaredFields();
	        for(int i = 0 ; i < fs.length; i++){
	        	Field f = fs[i];
	        	f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
	        	Object val = new Object();
	        	try {
	        		val = f.get(obj); // �õ������Ե�ֵ
	        		if(null != val && StringUtils.isNotBlank(val.toString())){
	        			val = URLDecoder.decode(val.toString(), "utf-8");
	        			parmMap.put(f.getName(), val);// ���ü�ֵ
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
	
	/**
	 * 对象转字符串
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public String toString(Object obj) {
		if(obj!=null){
			return obj.toString();
		} else {
			return "";
		}
	}
	public String toString(Object obj, String defaultValue) {
		if(obj!=null){
			return obj.toString();
		} else {
			return defaultValue;
		}
	}
	public Long toLong(Object obj) {
		if(obj!=null){
			return Long.valueOf(obj+"");
		} else {
			return null;
		}
	}
	public Long toLong(Object obj, long defaultValue) {
		if(obj!=null){
			return Long.valueOf(obj+"");
		} else {
			return defaultValue;
		}
	}
	public Integer toInt(Object obj) {
		if(obj!=null){
			return Integer.valueOf(obj+"");
		} else {
			return null;
		}
	}
	public Integer toInt(Object obj, int defaultValue) {
		if(obj!=null){
			return Integer.valueOf(obj+"");
		} else {
			return defaultValue;
		}
	}
	
	/**
	 * 时分转换 1位转2位
	 * @param time
	 * @return
	 */
	public static String timeConvert(String time) {
		return time.length() < 2 ? "0" + time : time;
	}
	
	public static Date now() {
		return new Date();
	}
	
	public static String timeStrConvert(String time) {
		if(StringUtils.isBlank(time)){
			return "";
		}
		String[] arr = time.split("-");
		String newTimeStr = "";

		String[] newStr = arr[0].split(":"); 
		newTimeStr += timeConvert(newStr[0])+":"+timeConvert(newStr[1]);
		newTimeStr += "-";
		newStr = arr[1].split(":");
		newTimeStr += timeConvert(newStr[0])+":"+timeConvert(newStr[1]);
			
		return newTimeStr;
	}
	
	public static void main(String[] args) {
		System.out.println(timeStrConvert("8:2-9:5"));
	}
	
}
