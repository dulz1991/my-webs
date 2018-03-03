package com.demo.my.base.util;

import javax.servlet.http.Cookie;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	private HttpServletRequest request;  
    private HttpServletResponse response;
	private int age;//����cookie�����೤���ɾ��
	
    public CookieUtil(HttpServletRequest request, HttpServletResponse response,int age) {  
        this.request = request;  
        this.response = response;  
        this.age=age;  
    }  
      
    public void addCookie(String name, String value) {  
        Cookie cookies = new Cookie(name, value);  
        cookies.setPath("/");  
//      cookies.setMaxAge(-1);//����cookie�����೤���ɾ�������0����˵������ɾ��������Ǹ����ͱ�����������ر�ʱ�Զ�ɾ����  
        cookies.setMaxAge(age);  
        response.addCookie(cookies);  
    }
    
    public String getCookieValue(String cookieName) {  
        if (cookieName != null) {  
            Cookie cookie = getCookie(cookieName);  
            if(cookie!=null){  
                return cookie.getValue();  
            }  
        }  
        return "";  
    }  
      
    public Cookie getCookie(String cookieName){  
        Cookie[] cookies = request.getCookies();  
        Cookie cookie = null;  
        try {  
            if (cookies != null && cookies.length > 0) {  
                for (int i = 0; i < cookies.length; i++) {  
                    cookie = cookies[i];  
                    if (cookie.getName().equals(cookieName)) {  
                        return cookie;  
                    }  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return cookie;  
    }  
      
    public boolean deleteCookie(String cookieName) {  
        if (cookieName != null) {  
            Cookie cookie = getCookie(cookieName);  
            if(cookie!=null){  
                cookie.setMaxAge(0);//���0����˵������ɾ��  
                cookie.setPath("/");//��Ҫ©��  
                response.addCookie(cookie);  
                return true;  
            }  
        }  
        return false;  
    }  
    public static void main(String[] args){  
//      CookieUtil util=new CookieUtil(request,response,-1);  
//      util.addCookie("name","value");  
//      String value=util.getCookieValue("name");  
//      System.out.println("value="+value);  
    }  
}
