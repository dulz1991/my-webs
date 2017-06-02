package com.demo.springboot.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Discovery;
import com.demo.my.base.model.User;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.util.Page;

@RestController
public class DiscoveryController extends BaseController {
	
	@Autowired
	private DiscoveryService discoveryService;
	@Autowired
	private UserService userService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping(value = "/discoveryList", method=RequestMethod.GET)
	public Map<String, Object> discoveryList() {
		Discovery discovery = new Discovery();
		discovery.setStatus(1);
		Page<Map<String, Object>> page = discoveryService.getMapListByParm(discovery, 1, 10, "d.ID desc");
		resMap.put("page", page);
		
		return resMap;
	}
	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/discoveryDetail", method=RequestMethod.GET)
	public Map<String, Object> discoveryDetail(Long id) {
		if(id==null){
			return responseError(ErrorConstant.ERROR_404, "页面没找到");
		}
		Map<String, Object> discovery = discoveryService.getMapById(id);
		if(discovery==null || !discovery.get("status").equals(1)){
			return responseError(ErrorConstant.ERROR_404, "页面没找到");
		}
		resMap.put("detail", discovery);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/auth/post", method=RequestMethod.POST)
	public Map<String, Object> doPost(Discovery discovery) {
		if(StringUtils.isBlank(discovery.getTitle())){
			return responseError(ErrorConstant.ERROR_GENERAL, "标题不能为空");
		}
		if(StringUtils.isBlank(discovery.getContent())){
			return responseError(ErrorConstant.ERROR_GENERAL, "内容不能为空");
		}
		
		discovery.setCreateTime(new Date());
		discovery.setUpdateTime(new Date());
		discovery.setClickNum(0);
		discovery.setStatus(1);
		discovery.setUserId(this.getCurrentUserId());
		
		return resMap;
	}
	 
}
