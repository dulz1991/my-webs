package com.demo.my.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.User;
import com.demo.my.base.servicebean.BlogServiceBean;
import com.demo.my.base.servicebean.UserServiceBean;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.converter.MapConverter;

@Controller
@RequestMapping("/auth/bloglog")
public class BlogLogController {
	
	@Resource(name = "userServiceBean")
	private UserServiceBean userService;
	@Resource(name = "blogServiceBean")
	private BlogServiceBean blogLogService;

	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView blogLog() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("blog/blog_log");
		return mv;
	}
	@ResponseBody
	@RequestMapping(value = "/getList", method=RequestMethod.GET)
	public Map<String, Object> blogLogList(String username, Integer pageNum, Integer pageSize) throws UnsupportedEncodingException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if (StringUtils.isNotBlank(username)) {
			username = URLDecoder.decode(username, "utf-8");
			User user = userService.getByUsername(username);
			if (user != null) {
				paramMap.put("userId", user.getId());	
			}
		}		
		
		PageUtil pageUtil = new PageUtil(pageNum, pageSize);
		List<Map<String, Object>> list = blogLogService.getMapListByParm(pageUtil, paramMap, KeyConstant.MAPPER_BLOG_LOG);
		list = new MapConverter().map2Map(list, null);
		Integer count = blogLogService.countByParm(paramMap, KeyConstant.MAPPER_BLOG_LOG);
		
		pageUtil = new PageUtil(pageUtil.getCurrentPage(), pageUtil.getPageSize(), count);
		resMap.put("list", list);
		resMap.put("page", pageUtil);

		return resMap;
	}
	
}

