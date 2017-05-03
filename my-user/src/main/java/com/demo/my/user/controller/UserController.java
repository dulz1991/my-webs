package com.demo.my.user.controller;

import java.util.ArrayList;
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

import com.demo.my.base.bean.User;
import com.demo.my.base.servicebean.UserServiceBean;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.converter.BeanConverter;

@Controller
@RequestMapping("/auth/user")
public class UserController {
	
	@Resource(name = "userServiceBean")
	private UserServiceBean userService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView users(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/user_list");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getList", method=RequestMethod.GET)
	public Map<String, Object> getUserList(@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,@RequestParam(value="pageSize", defaultValue="10") Integer pageSize,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String username = request.getParameter("username");
		User user = new User();
		if(StringUtils.isNotBlank(username)){
			user.setUsername(username);	
		}
		List<User> list = userService.getByParm(new PageUtil(pageNum, pageSize), user); 
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		try {
			mapList = new BeanConverter().bean2Map(list, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer count = userService.getByParm_count(user);
		
		resMap.put("list", mapList);
		resMap.put("page", new PageUtil(pageNum, pageSize, count));
		return resMap;
	}
	
}
