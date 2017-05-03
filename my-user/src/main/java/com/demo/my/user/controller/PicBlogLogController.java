package com.demo.my.user.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
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

import com.demo.my.base.bean.PicBlogLog;
import com.demo.my.base.bean.User;
import com.demo.my.base.servicebean.BlogServiceBean;
import com.demo.my.base.servicebean.PicBlogServiceBean;
import com.demo.my.base.servicebean.UserServiceBean;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.converter.BeanConverter;

@Controller
@RequestMapping("/auth/picbloglog")
public class PicBlogLogController {
	
	@Resource(name = "userServiceBean")
	private UserServiceBean userService;
	@Resource(name = "picBlogServiceBean")
	private PicBlogServiceBean picBlogLogService;
	@Resource(name = "blogServiceBean")
	private BlogServiceBean blogLogService;

	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView picBlogLog() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("picblog/pic_blog_log");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getList", method=RequestMethod.GET)
	public Map<String, Object> picBlogLogList(String username, Integer pageNum, Integer pageSize) throws UnsupportedEncodingException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
		/*List<Map<String, Object>> list = blogLogService.getMapListByParm(pageUtil, paramMap, KeyConstant.MAPPER_PIC_LOG);
		list = new MapConverter().map2Map(list, null);*/
		List<PicBlogLog> list = picBlogLogService.getBeanListByParm(pageUtil, paramMap, KeyConstant.MAPPER_PIC_LOG);
		List<Map<String, Object>> mapList = new BeanConverter().bean2Map(list, null);
		Integer count = picBlogLogService.countByParm(paramMap, KeyConstant.MAPPER_PIC_LOG);
		
		pageUtil = new PageUtil(pageUtil.getCurrentPage(), pageUtil.getPageSize(), count);
		resMap.put("list", mapList);
		resMap.put("page", pageUtil);

		return resMap;
	}
	
}

