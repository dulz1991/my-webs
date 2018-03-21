package com.demo.my.blog.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Active;
import com.demo.my.base.model.ActiveUser;
import com.demo.my.base.service.ActiveService;
import com.demo.my.base.service.ActiveUserService;
import com.demo.my.base.service.UserService;
import com.demo.my.blog.controller.BaseController;
import com.ibm.db2.jcc.a.ac;

@Controller
public class ActiveController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActiveService activeService;
	
	@Autowired
	private ActiveUserService activeUserService;
	
	@ResponseBody
	@RequestMapping("/auth/addActive")
	public Map<String, Object> addActive(Active active){
		Map<String, Object> resMap = this.responseOK("");
		
		Map<String, Object> checkMap = checkActive(active);
		if(!checkMap.get(ErrorConstant.ERROR_NO).equals(ErrorConstant.ERROR_200)){
			return checkMap;
		}
		
		//保存活动
		Long userId = this.getCurrentUserId();
		active.setCreaterId(userId);
		activeService.save(active);
		
		//记录参加活动用户
		ActiveUser activeUser = new ActiveUser();
		activeUser.setActiveId(active.getId());
		activeUser.setCreateTime(new Date());
		activeUser.setUserId(userId);
		activeUserService.save(activeUser);
		
		return resMap;
	}
	
	private Map<String, Object> checkActive(Active active) {
		if(StringUtils.isBlank(active.getActiveTitle())){
			return this.responseError(-1, "活动标题不能为空");
		} else if(active.getActiveTitle().length()>20){
			return this.responseError(-1, "活动标题不能超过20个字");
		} else if(StringUtils.isBlank(active.getActiveContent())){
			return this.responseError(-1, "活动内容不能为空");
		} else if(active.getActiveContent().length()>1000){
			return this.responseError(-1, "活动内容不能超过1000字");
		} else if(active.getStartDate()==null || active.getStartTime()==null){
			return this.responseError(-1, "活动开始日期和时间不能为空");
		} else if(active.getEndDate()==null || active.getEndTime()==null){
			return this.responseError(-1, "活动结束日期和时间不能为空");
		}
		return this.responseOK("");
	}
	
}
