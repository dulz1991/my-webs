package com.demo.my.user.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.User;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.BlogServiceBean;
import com.demo.my.base.servicebean.CodeServiceBean;
import com.demo.my.base.servicebean.PicBlogServiceBean;
import com.demo.my.base.servicebean.UserServiceBean;

@Controller
@RequestMapping("/auth/admin")
public class AdminController extends BaseController {
	
	@Resource(name = "userServiceBean")
	private UserServiceBean userService;
	@Resource(name = "blogServiceBean")
	private BlogServiceBean blogService;
	@Resource(name = "picBlogServiceBean")
	private PicBlogServiceBean picBlogService;
	@Resource(name = "codeServiceBean")
	private CodeServiceBean codeService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("userCount", userService.getByParm_count(new User()));
		modelAndView.addObject("blogCount", blogService.countByParm(new HashMap<String, Object>(), KeyConstant.MAPPER_BLOG));
		modelAndView.addObject("picBlogCount", picBlogService.countByParm(new HashMap<String, Object>(), KeyConstant.MAPPER_PIC));
		modelAndView.addObject("handbookCount", codeService.countByParm(new HashMap<String, Object>(), KeyConstant.MAPPER_CODE));
		return modelAndView;
	}
	
	@RequestMapping(value = "/profile", method=RequestMethod.GET)
	public ModelAndView profile(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/profile");
		modelAndView.addObject("user", this.getCurrentUser());
		return modelAndView;
	}
	
}
