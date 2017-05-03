package com.demo.my.blog.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.User;
import com.demo.my.base.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
	
	@Autowired
	private AccountService accountService;
	
	Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		if(this.getCurrentUser()==null){
			modelAndView.setViewName("account/login");
			return modelAndView;
		} 
		SavedRequest savedRequest = getSavedRequest();
		if(savedRequest==null){
			modelAndView.setViewName("account/login");
			return modelAndView;
		}
		String url = savedRequest.getRequestUrl();
		if(url.indexOf("login")<=0){
			return new ModelAndView("redirect:"+url);
		} else {
			modelAndView.setViewName("account/login");
			return modelAndView;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/doLogin", method=RequestMethod.GET)
	public Map<String, Object> doLogin(HttpServletRequest request, User user) {
		Map<String, Object> resMap = accountService.login(user);
		return resMap;
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, User user) {
		ModelAndView modelAndView = new ModelAndView();
		accountService.logout();
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	@RequestMapping(value = "/doJump", method=RequestMethod.GET)
	public ModelAndView doJump(Integer role) {
		ModelAndView modelAndView = new ModelAndView();
		if (role == 1) {
			modelAndView.setViewName("redirect:http://my.user/admin");
		} else {
			modelAndView.setViewName("redirect:http://my.user/user");
		}
		return modelAndView;
	}
	
}
