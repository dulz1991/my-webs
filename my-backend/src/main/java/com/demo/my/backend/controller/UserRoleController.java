package com.demo.my.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.service.UserRoleService;
import com.demo.my.base.model.UserRole;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/userRole")
public class UserRoleController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);
	
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("user/user_role_list");
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("user/user_role_edit");
		if(id!=null){
			UserRole entity = userRoleService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new UserRole());
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(UserRole userRole,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		Page<Map<String, Object>> page = userRoleService.getPage("UserRoleMapper.getBeanListByParm", parmMap);
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(UserRole userRole) {
		if(StringUtils.isBlank(userRole.getRoleName())){
			return responseError(-1, "权限名称不能为空");
		}
		userRoleService.save(userRole);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = userRoleService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

}
