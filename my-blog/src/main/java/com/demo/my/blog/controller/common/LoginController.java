package com.demo.my.blog.controller.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.model.User;
import com.demo.my.base.service.LoginService;
import com.demo.my.blog.controller.common.BaseController;


@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private LoginService loginService;
	

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
	@RequestMapping(value = "/doLogin")
	public Map<String, Object> doLogin(HttpServletRequest request, User user) {
		Map<String, Object> resMap = loginService.login(user);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/doLogout", method=RequestMethod.GET)
	public Map<String, Object> doLogout(HttpServletRequest request, User user) {
		Map<String, Object> resMap = loginService.logout();
		return resMap;
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, User user) {
		ModelAndView modelAndView = new ModelAndView();
		loginService.logout();
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
	
	@ResponseBody
	@RequestMapping(value = "/doRegist", method=RequestMethod.POST)
	public Map<String, Object> doRegist(User user) {
		Map<String, Object> resMap = loginService.regist(user);
		resMap.put("tip", "注册成功, 请登录");
		return resMap;
	}
	
}
