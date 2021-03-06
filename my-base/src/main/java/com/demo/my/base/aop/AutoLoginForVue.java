package com.demo.my.base.aop;

import java.util.List;

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
import com.demo.my.base.service.SysUserRoleService;
import com.demo.my.base.service.UserRoleService;
import com.demo.my.base.service.UserService;

@Aspect
@Component
public class AutoLoginForVue {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Pointcut("execution (* com.demo.my.*.controller.*Controller.loginResult(..))")
	public void autoLoginAopForVue() { }

	/**
	 * joinPoint 切点
	 * @param joinPoint
	 */
	@Before("autoLoginAopForVue()")
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
	                
	                //权限
	                List<String> roleCodeList = sysUserRoleService.getRoleCodeByUserId(user.getId());
					if(!roleCodeList.isEmpty()){
						user.setRoleCodeList(roleCodeList);
					}
					
	                subject.getSession().setAttribute(KeyConstant.USER_INFO, user);
	            }
	        }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	 @After("autoLoginAopForVue()") 
	 public void doAfter(JoinPoint joinPoint) { 
		 
	 }
	 
}
