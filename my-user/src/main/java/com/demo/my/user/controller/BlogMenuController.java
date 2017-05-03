package com.demo.my.user.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.BlogMenu;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.BlogServiceBean;

@Controller
@RequestMapping("/auth/blogmenu")
public class BlogMenuController extends BaseController {
	
	@Resource(name = "blogServiceBean")
	private BlogServiceBean blogMenuService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView users(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("blog/blog_menu");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public HashMap<String, Object> save(BlogMenu blogMenu) {
		if (StringUtils.isBlank(blogMenu.getName())) {
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_MENU_NAME);
		}
		if (blogMenu.getId() != null && blogMenu.getId() != 0L) {
			blogMenuService.update(blogMenu, KeyConstant.MAPPER_BLOG_MENU);
		} else {
			blogMenuService.insert(blogMenu, KeyConstant.MAPPER_BLOG_MENU);
		}
		return responseOK("");
	}
	
	@ResponseBody
	@RequestMapping(value = "/getList", method=RequestMethod.GET)
	public HashMap<String, Object> list() {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		List<BlogMenu> list = blogMenuService.getBeanListByParm(null, null, KeyConstant.MAPPER_BLOG_MENU); 
		resMap.put("list", list);
		return resMap;
	}
	
}
