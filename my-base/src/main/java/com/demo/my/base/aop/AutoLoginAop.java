package com.demo.my.base.aop;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.User;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.service.UserService;

@Aspect
@Component
public class AutoLoginAop {

	@Autowired
	private UserService userService;

	@Pointcut("execution (* com.demo.my.*.controller.*Controller.login(..))")
	public void autoLoginAop() { }

	/**
	 * joinPoint 切点
	 * @param joinPoint
	 */
	@Before("autoLoginAop()")
	public void doBefore(JoinPoint joinPoint) {
		try {
			Subject subject = SecurityUtils.getSubject();
			if (!subject.isAuthenticated() && subject.isRemembered()) {
	            Object principal = subject.getPrincipal();
	            if (null != principal) {
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

	
	 @After("autoLoginAop()") 
	 public void doAfter(JoinPoint joinPoint) { 
		 
	 }
	 
}
