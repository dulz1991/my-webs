package com.demo.my.base.aop;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.demo.my.base.bean.User;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.UserServiceBean;

@Aspect
@Component
public class AutoLoginAop {

	@Resource(name = "userServiceBean")
	private UserServiceBean userService;

	@Pointcut("execution (* com.demo.my.*.controller.AccountController.login(..))")
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
