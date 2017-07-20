package com.demo.springboot.interception;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.model.User;
import com.demo.my.base.service.UserService;
import com.demo.my.base.util.CookieUtil;
import com.demo.my.base.util.SpringContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    	//vue post跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 允许带上 cookie
        // Request methods you wish to allow
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
        // Request headers you wish to allow
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
    	
    	String url=request.getRequestURL().toString();
    	if(url.contains("isLogin")){
    		try {
    			Subject subject = SecurityUtils.getSubject();
    			if (!subject.isAuthenticated() && subject.isRemembered()) {
    	            Object principal = subject.getPrincipal();
    	            if (null != principal) {
    	            	UserService userService = SpringContextUtil.getBean("userService");
    	                User user = userService.getByUsername(String.valueOf(principal));
    	                String password = user.getPassword();
    	                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), password);
    	                token.setRememberMe(true);
    	                subject.login(token);//登录
    	                user.setPassword(null);
    	                subject.getSession().setAttribute(KeyConstant.USER_INFO, user);
    	            }
    	        }
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    		}
    	}
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
