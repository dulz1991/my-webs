package com.demo.my.user.aop;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.demo.my.base.bean.Blog;
import com.demo.my.base.bean.BlogLog;
import com.demo.my.base.bean.User;
import com.demo.my.base.servicebean.BlogServiceBean;

@Aspect
@Component
public class BlogAop {
	
	@Resource(name = "blogServiceBean")
	private BlogServiceBean blogService;
	
	@Pointcut("execution(* com.demo.my.user.controller.BlogController.saveBlog(..))")
    public void save(){};
    
    @After("save()")
    public void after(JoinPoint point) {
    	Object[] objs = point.getArgs();
    	Blog blog = (Blog) objs[0];
    	BlogLog log = new BlogLog();
    	if (blog.getId() != null) {
			log.setRemark("update blog");
		} else {
			log.setRemark("create blog");
		}
		log.setBlogId(blog.getId());
		Subject subject = SecurityUtils.getSubject();
    	User user = (User) subject.getSession().getAttribute("userInfo");
    	log.setUserId(user.getId());
    	blogService.insert(log, "blogMapper");
    } 
  
}
