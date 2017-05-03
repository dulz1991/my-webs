package com.demo.my.blog.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.Blog;
import com.demo.my.base.bean.BlogMenu;
import com.demo.my.base.bean.User;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.BlogServiceBean;
import com.demo.my.base.servicebean.UserServiceBean;
import com.demo.my.base.util.PageUtil;

@Controller
public class BlogController extends BaseController {
	
	@Resource(name = "blogServiceBean")
	private BlogServiceBean blogService;
	@Resource(name = "userServiceBean")
	private UserServiceBean userService;
	
	@RequestMapping(value = "/auth/blog/index", method=RequestMethod.GET)
	public ModelAndView index(Blog blog) throws UnsupportedEncodingException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("blog/index");
		
		User user = getCurrentUser();
		
		List<BlogMenu> menuList = blogService.getBeanListByParm(null, null, KeyConstant.MAPPER_BLOG_MENU);
		blog.setMenuId(-1L);
		Map<Long, Integer> menuMap = new HashMap<Long, Integer>();
		HashMap<String, Object> parmMap = new HashMap<String, Object>();
		for (BlogMenu menu : menuList) {
			parmMap.put("menuId", menu.getId());
			parmMap.put("status", 0);
			parmMap.put("userId", user.getId());
			Integer count = blogService.countByParm(parmMap, KeyConstant.MAPPER_BLOG);
			menuMap.put(menu.getId(), count);
		}
		
		if (user != null) {
			modelAndView.addObject("role", user.getRole());
			modelAndView.addObject("isLogin", true);
			modelAndView.addObject("username", user.getUsername());
		} else {
			modelAndView.addObject("role", -1);
			modelAndView.addObject("isLogin", false);
		}
		
		modelAndView.addObject("menuList", menuList);
		modelAndView.addObject("menuMap", menuMap);
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/auth/blog/queryBlogList", method = RequestMethod.GET)
	public HashMap<String, Object> blogList(Blog blog, PageUtil pageUtil) throws UnsupportedEncodingException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		int pageNum = pageUtil.getPageNum();
		pageUtil = new PageUtil(pageUtil.getPageNum(), pageUtil.getPageSize());
		paramMap.put("status", 0);
		if (blog.getMenuId() != null && blog.getMenuId() > 0) {
			paramMap.put("menuId", blog.getMenuId());
		}
		if(StringUtils.isNotBlank(blog.getTitle())){
			String title = URLDecoder.decode(blog.getTitle(), "utf-8");
			paramMap.put("title", title);	
		}
		User user = getCurrentUser();
		paramMap.put("userId", user.getId());
		
		List<Map<String, Object>> list = blogService.getMapListByParm(pageUtil, paramMap, KeyConstant.MAPPER_BLOG);
		Integer count = blogService.countByParm(paramMap, KeyConstant.MAPPER_BLOG);
		
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		pageUtil = new PageUtil(pageUtil.getPageNum(), pageUtil.getPageSize(), count);
		resMap.put("list", list);
		resMap.put("page", new PageUtil(pageNum, pageUtil.getPageSize(), count));
		return resMap;
	}
	
	@RequestMapping(value = "/auth/blog/blogDetail", method=RequestMethod.GET)
	public ModelAndView blog(HttpServletRequest request, HttpServletResponse response, Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("blog/blogDetail");
		
		Blog blog = blogService.getById(id, KeyConstant.MAPPER_BLOG);
		if (blog == null) {
			modelAndView.addObject("blog", new Blog());	
		} else {
			Long clickNum = blog.getClick();
			if(null==clickNum){
				blog.setClick(1L);
			}else{
				blog.setClick(blog.getClick() + 1);	
			}
			modelAndView.addObject("blog", blog);
		}
		User blogUser = userService.getById(blog.getUserId());
		modelAndView.addObject("username", blogUser.getUsername());
		
		User user = getCurrentUser();
		if (user != null) {
			modelAndView.addObject("role", user.getRole());
			modelAndView.addObject("isLogin", true);
		} else {
			modelAndView.addObject("role", -1);
			modelAndView.addObject("isLogin", false);
		}
		
		Blog entity = new Blog();
		entity.setId(id);
		entity.setClick(blog.getClick());
		blogService.update(entity, KeyConstant.MAPPER_BLOG);
		return modelAndView;
	}

	
}
