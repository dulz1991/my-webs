package com.demo.my.backend.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.model.User;
import com.demo.my.base.service.LoginService;

@Controller
public class LoginController extends BaseBackendController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		if(this.getCurrentUser()==null){
			modelAndView.setViewName("login");
			return modelAndView;
		} 
		SavedRequest savedRequest = getSavedRequest();
		if(savedRequest==null){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		String url = savedRequest.getRequestUrl();
		if(url.indexOf("login")<=0){
			return new ModelAndView("redirect:"+url);
		} else {
			modelAndView.setViewName("login");
			return modelAndView;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/doLogin", method=RequestMethod.GET)
	public Map<String, Object> doLogin(HttpServletRequest request, User user) {
		Map<String, Object> resMap = loginService.login(user);
		return resMap;
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login");
		loginService.logout();
		/*if ((Integer) resMap.get("role") == 1) {
			String url = (String) resMap.get("url");
			modelAndView.setViewName("redirect:/login");
		} else {
			modelAndView.setViewName("redirect:/login");
		}*/
		return modelAndView;
	}
	
}
