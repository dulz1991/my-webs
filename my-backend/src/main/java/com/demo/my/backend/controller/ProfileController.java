package com.demo.my.backend.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.model.User;
import com.demo.my.base.service.UserService;

@Controller
@RequestMapping("/backend/profile")
public class ProfileController extends BaseBackendController {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/index")
	public ModelAndView index(String userId) {
		ModelAndView mv = new ModelAndView("profile/index");
		
		if(StringUtils.isBlank(userId)){
			User user = this.getCurrentUser();
			mv.addObject("user", user);
			mv.addObject("isSelf", true);
		} else {
			User user = userService.getById(Long.valueOf(userId));
			mv.addObject("user", user);
			mv.addObject("isSelf", false);
		}
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/changeUserStatus")
	public Map<String, Object> changeUserStatus(User user) {
		int i = userService.update(user);
		if(i>0){
			return this.responseOK("");
		} else {
			return this.responseGeneralError("更新数据库失败");
		}
	}
	
}
