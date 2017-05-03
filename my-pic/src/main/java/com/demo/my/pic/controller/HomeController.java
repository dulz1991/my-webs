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
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.PicBlogServiceBean;
import com.demo.my.base.util.PageUtil;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
	
	@Resource(name = "picBlogServiceBean")
	private PicBlogServiceBean picBlogService;

	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home/index");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("needPic", true);
		paramMap.put("isDeleted", 0);
		List<Map<String, Object>> list = picBlogService.getMapListByParm(new PageUtil(1, 6), paramMap, KeyConstant.MAPPER_PIC);
		modelAndView.addObject("list", list);
		modelAndView.addObject("isLogin", this.isLogin());
		return modelAndView;
	}

}
