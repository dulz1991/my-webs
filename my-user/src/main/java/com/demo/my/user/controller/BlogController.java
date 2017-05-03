package com.demo.my.user.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.Blog;
import com.demo.my.base.bean.BlogMenu;
import com.demo.my.base.bean.User;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.BlogServiceBean;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.converter.MapConverter;

@Controller
@RequestMapping("/auth/blog")
public class BlogController extends BaseController {

	@Resource(name = "blogServiceBean")
	private BlogServiceBean blogService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("blog/blog_list");
		List<BlogMenu> menuList = blogService.getBeanListByParm(null, null, KeyConstant.MAPPER_BLOG_MENU);
		mv.addObject("menuList", menuList);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public HashMap<String, Object> list(Blog blog, PageUtil pageUtil)
			throws UnsupportedEncodingException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		pageUtil = new PageUtil(pageUtil.getPageNum(), pageUtil.getPageSize());
		if (StringUtils.isNotBlank(blog.getTitle())) {
			String title = URLDecoder.decode(blog.getTitle(), "utf-8");
			paramMap.put("title", title);
		}
		if (StringUtils.isNotBlank(blog.getUsername())) {
			String username = URLDecoder.decode(blog.getUsername(), "utf-8");
			paramMap.put("username", username);
		} else {
			User user = this.getCurrentUser();
			if (user != null && user.getRole() != 1) {
				paramMap.put("username", user.getUsername());
			}
		}
		if (blog.getMenuId() != null && blog.getMenuId() != -1) {
			paramMap.put("menuId", blog.getMenuId());
		}
		List<Map<String, Object>> list = blogService.getMapListByParm(pageUtil, paramMap, KeyConstant.MAPPER_BLOG);
		list = new MapConverter().map2Map(list, null);
		Integer count = blogService.countByParm(paramMap, KeyConstant.MAPPER_BLOG);

		HashMap<String, Object> resMap = new HashMap<String, Object>();
		pageUtil = new PageUtil(pageUtil.getCurrentPage(), pageUtil.getPageSize(), count);
		resMap.put("list", list);
		resMap.put("page", pageUtil);

		return resMap;
	}

	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.GET)
	public HashMap<String, Object> doDelete(@RequestParam(value = "id", defaultValue = "0") Long id) {
		Blog blog = blogService.getById(id, KeyConstant.MAPPER_BLOG);
		if (null == blog) {
			return responseError(ErrorConstant.ERROR_404, ErrorConstant.ERROR_NO_RECORD);
		}
		blogService.delete(id, KeyConstant.MAPPER_BLOG);
		return responseOK(ErrorConstant.TIP_DELETE_SUCCESS);
	}

}
