package com.demo.my.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.model.User;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		User user = getCurrentUser();
		if (user != null) {
			modelAndView.addObject("role", user.getRole());
			modelAndView.addObject("username", user.getUsername());
		} else {
			modelAndView.addObject("role", null);
		}
		return modelAndView;
	}
	
}
