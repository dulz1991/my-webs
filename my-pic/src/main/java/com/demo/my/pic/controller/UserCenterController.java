package com.demo.my.pic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.PicBlog;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.PicBlogServiceBean;
import com.demo.my.base.util.PageUtil;

@Controller
@RequestMapping(value = "/auth/user")
public class UserCenterController extends BaseController{
	
	@Resource(name = "picBlogServiceBean")
	private PicBlogServiceBean picBlogService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/index");
		modelAndView.addObject("isLogin", this.isLogin());
		return modelAndView;
	}
	
	@RequestMapping(value = "/myPicList", method=RequestMethod.GET)
	public ModelAndView myPicList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/list");
		modelAndView.addObject("isLogin", this.isLogin());
		
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", this.getCurrentUserId());
		List<PicBlog> list = picBlogService.getBeanListByParm(new PageUtil(0, 100), parmMap, KeyConstant.MAPPER_PIC);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getList", method=RequestMethod.GET)
	public HashMap<String, Object> getList(PicBlog picBlog) {
		return null;
	}
	
}
